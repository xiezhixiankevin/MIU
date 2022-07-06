package com.example.miu.controller;


import com.example.miu.Resp.BaseResp;
import com.example.miu.constant.enums.RespEnum;
import com.example.miu.constant.exception.MIUException;
import com.example.miu.pojo.table.User;
import com.example.miu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
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

    @PostMapping("/update")
    public BaseResp updateUser(@RequestBody User user){
        try{
            userService.updateUser(user);
            log.info("UserController updateUser success user:{}",user);
            return BaseResp.success();
        }catch (MIUException e){
            log.error("UserController updateUser error user:{}, e:",user,e);
            return BaseResp.failed(RespEnum.getRespEnumByCode(e.getCode()));
        }catch (Exception e){
            log.error("UserController updateUser error user:{}, e:",user,e);
            return BaseResp.failed(RespEnum.DEFAULT_FAIL);
        }
    }

    @GetMapping("/delete")
    public BaseResp deleteUser(@RequestParam String email){
        try{
            userService.deleteUser(email);
            log.info("UserController deleteUser success email:{}",email);
            return BaseResp.success();
        }catch (Exception e){
            log.error("UserController updateUser error email:{}, e:",email,e);
            return BaseResp.failed(RespEnum.DEFAULT_FAIL);
        }
    }

    @GetMapping("/userExisted")
    public BaseResp ifUserExisted(@RequestParam String email){
        try{
            Boolean result =  userService.ifUserExisted(email)
            log.info("UserController queryIfUserExisted email:{}, result:{}",email,result);
            return BaseResp.success();
        }catch (Exception e){
            log.error("UserController updateUser error email:{}, e:",email,e);
            return BaseResp.failed(RespEnum.DEFAULT_FAIL);
        }
    }
}

