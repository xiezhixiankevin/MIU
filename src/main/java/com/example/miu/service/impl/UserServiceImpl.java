package com.example.miu.service.impl;

import com.example.miu.pojo.table.User;

import com.example.miu.mapper.UserMapper;
import com.example.miu.pojo.table.UserExample;
import com.example.miu.service.EmailService;
import com.example.miu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User registerUser(User user) {

        if (ifExistUser(user.getEmail())){
            return null;
        }

        // 否则注册为新的用户
        userMapper.insertSelective(user);

        /* 成功后告知对方已成功注册 */
        emailService.sendSimpleMail(user.getEmail() , "Register Notice" , "Register successfully! Welcome to use our products, if you have any " +
                "comments, please feel free to feedback, we will actively improve, thank you.");

        return user;
    }

    @Override
    public User loginUser(String username, String text,boolean type) {
        return null;
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
    public boolean ifExistUser(String username) {return false;
    }

    @Override
    public int deleteUser(String email) {
        return 0;
    }

    @Override
    public int updateUser(int userId, User user) {
        return 0;
    }
}
