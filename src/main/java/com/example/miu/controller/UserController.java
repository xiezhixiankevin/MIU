package com.example.miu.controller;


import com.example.miu.pojo.table.User;
import com.example.miu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(String username, String password) {
        User user = userService.loginUser(username, password);
        if (user == null) {
            return "fail";
        }
        return "success";
    }
}

