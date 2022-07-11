package com.example.miu.service.impl;

import cn.hutool.json.JSONUtil;
import com.example.miu.cache.TokenCache;
import com.example.miu.constant.enums.RespEnum;
import com.example.miu.constant.exception.MIUException;
import com.example.miu.mapper.UserMapper;
import com.example.miu.pojo.table.User;


import com.example.miu.pojo.table.UserExample;

import com.example.miu.service.EmailService;

import com.example.miu.service.UserService;
import com.example.miu.utils.Global;
import com.example.miu.utils.LoginUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.File;

import java.util.List;

/**
 * <Description> UserServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName UserServiceImpl
 * @taskId
 * @see com.example.miu.service.impl
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final long SESSION_TIME_OUT = 7200*1000L;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailService emailService;
    @Resource
    private TokenCache tokenCache;

    @Override
    public boolean registerUserOfEmail(String email) {
        if (ifUserExisted(email)){
            return false;
        }
        User user = new User();
        user.setEmail(email);
        userMapper.insertSelective(user);

        String savePath = "/home/project/miu/images/user/" + user.getId();
        File folder = new File(savePath);
        //不存在则创建文件夹
        if (!folder.exists()){
            folder.mkdir();
        }
        return true;
    }

    @Override
    public User registerUserOfUsernameAndPassword(String email, String username, String password) {
        User userByEmail = getUserByEmail(email);
        if (userByEmail.getUsername()!=null)
            return null;

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        userMapper.updateByExampleSelective(user,userExample);


        return loginUser(email,"",Global.LOGIN_CODE);
    }

    @Override
    public User loginUser(String email, String text,boolean type) {
        User user = getUserByEmail(email);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(email, text);
        SecurityUtils.getSubject().login(usernamePasswordToken);
        if(!SecurityUtils.getSubject().isAuthenticated()){
            throw new MIUException(RespEnum.ACCOUNT_INVALID);
        }
        SecurityUtils.getSubject().getSession().setTimeout(SESSION_TIME_OUT);
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("CORE_SESSION_INFO_KEY", JSONUtil.toJsonStr(user));
        user = LoginUserUtil.getLoginUserInfo();

        if(user != null && user.getId() != null){
            tokenCache.setDataToCache(String.valueOf(user.getId()), String.valueOf(session.getId()));
        }
        log.info("session:{}",session);
        //通过验证码登录,直接返回user,之前已经校验过验证码
        return user;
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        User user = new User();
        user.setPassword(newPassword);
        userMapper.updateByExampleSelective(user,userExample);
    }


    @Override
    public List<User> listUser() {
        return userMapper.selectByExample(null);
    }

    @Override
    public List<User> listUserSelective(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (user.getGender() != null){
            criteria.andGenderEqualTo(user.getGender());
        }
        return userMapper.selectByExample(userExample);
    }

    @Override
    public User getUserByUserName(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        return userList.isEmpty()? null:userList.get(0);
    }

    @Override
    public User getUserByEmail(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        List<User> userList = userMapper.selectByExample(userExample);
        return userList.isEmpty()? null:userList.get(0);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public boolean ifUserExisted(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        return !userMapper.selectByExample(userExample).isEmpty();
    }

    @Override
    public boolean ifEmailExistedButNoUsername(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        List<User> userList = userMapper.selectByExample(userExample);
        if (!userList.isEmpty()){
            return userList.get(0).getUsername() == null;
        }
        return false;
    }

    @Override
    public int deleteUser(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        return userMapper.deleteByExample(userExample);
    }

    @Override
    public int updateUser(User user) {
        if(user == null || user.getId() == null){
            throw new MIUException(RespEnum.USER_ID_NULL);
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
