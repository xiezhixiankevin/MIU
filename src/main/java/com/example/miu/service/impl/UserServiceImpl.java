package com.example.miu.service.impl;

import com.example.miu.pojo.table.User;
import com.example.miu.service.UserService;
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
        return null;
    }

    @Override
    public List<User> listUserSelective(User user) {
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
