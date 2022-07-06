package com.example.miu.service.impl;

import com.example.miu.pojo.table.User;

import com.example.miu.mapper.UserMapper;
import com.example.miu.pojo.table.UserExample;
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

    @Override
    public User registerUser(User user) {
        return null;
    }

    @Override
    public User loginUser(String username, String password) {
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
        return null;
    }

    @Override
    public User getUserByUserName(String username) {
        return null;
    }

    @Override
    public boolean ifExistUser(String username) {
        return false;
    }

    @Override
    public int deleteUser(String username) {
        return 0;
    }

    @Override
    public int updateUser(int userId, User user) {
        return 0;
    }
}
