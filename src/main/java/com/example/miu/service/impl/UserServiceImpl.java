package com.example.miu.service.impl;

import com.example.miu.constant.enums.RespEnum;
import com.example.miu.constant.exception.MIUException;
import com.example.miu.mapper.UserMapper;
import com.example.miu.pojo.table.User;

import com.example.miu.mapper.UserMapper;
import com.example.miu.pojo.table.UserExample;

import com.example.miu.service.EmailService;

import com.example.miu.service.UserService;
import com.example.miu.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailService emailService;


    @Override
    public boolean registerUserOfEmail(String email) {
        if (ifUserExisted(email)){
            return false;
        }
        User user = new User();
        user.setEmail(email);
        userMapper.insertSelective(user);
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
        if (type == Global.LOGIN_PASSWORD){
            if (user.getPassword().equals(text)){
                return user;
            }
            return null;
        }
        //通过验证码登录,直接返回user,之前已经校验过验证码
        return user;
    }

    @Override
    public String retrievePassword(String username) {
        return null;
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

    public boolean ifUserExisted(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        return !userMapper.selectByExample(userExample).isEmpty();
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
        return userMapper.updateByPrimaryKey(user);
    }
}
