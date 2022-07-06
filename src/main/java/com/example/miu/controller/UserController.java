package com.example.miu.controller;
import com.example.miu.entity.User;
import com.example.miu.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(path = "/login",method = RequestMethod.GET)

    public String login(String username,String password){
        User user= userMapper.selectByUsernameAndPassword(username,password);
        if(user ==null){
            return "fail";
        }
        return "success";
    }

}
