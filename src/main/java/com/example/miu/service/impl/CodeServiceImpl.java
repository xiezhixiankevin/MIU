package com.example.miu.service.impl;

import com.example.miu.pojo.logic.Code;
import com.example.miu.service.CodeService;
import com.example.miu.service.EmailService;
import com.example.miu.service.UserService;
import com.example.miu.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <Description> CodeServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName CodeServiceImpl
 * @taskId

 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @Override
    public int sendCode( String email,int type) {
        if (type == Global.REGISTER_LOGIN){
            return sendCodeREGISTER(email);
        }else if (type == Global.FIND){
            return sendCodeFIND(email);
        }
        return Global.FAIL;
    }



    @Override
    public int checkCode(Code code, int type) {
        String realCode = getCode(code.getEmail(), type);
        if (realCode.equals(code.getCodeValue())){
            return Global.SUCCESS;
        }
        return Global.FAIL;
    }


    @Override
    public String getCode(String email,int type) {
        String key = email + "_";
        if (type == Global.REGISTER_LOGIN){
            key += "code";
        }else {
            key += "find_code";
        }
        Object result = redisTemplate.opsForValue().get(key);
        if (result == null){
            return "";
        }
        return (String) result;
    }

    //发送注册验证码
    private int sendCodeREGISTER(String email){
        //先判断之前发的验证码有没有失效
        if (getCode(email,Global.REGISTER_LOGIN).length()>0)
            return Global.FAIL;
        long codeL = System.nanoTime();
        //先生成6位验证码
        String codeStr = Long.toString(codeL);
        codeStr = codeStr.substring(codeStr.length() - 8, codeStr.length() - 2);
        //存入redis
        String key_code = email + "_code";
        redisTemplate.opsForValue().set(key_code,codeStr,60*5,TimeUnit.SECONDS);//验证码有效时间是5分钟

        //发送到用户邮箱
        String content = "你好，欢迎使用miu,验证码是:"+ codeStr +",有效时间5分钟";
        emailService.sendSimpleMail(email,"miu验证码",content);
        return Global.SUCCESS;

    }

    //发送找回密码验证码
    private int sendCodeFIND(String email){
        //先判断之前发的验证码有没有失效
        if (getCode(email,Global.FIND).length()>0)
            return Global.FAIL;

        long codeL = System.nanoTime();
        //先生成6位验证码
        String codeStr = Long.toString(codeL);
        codeStr = codeStr.substring(codeStr.length() - 8, codeStr.length() - 2);
        //存入redis
        String key_code = email + "_find_code";
        redisTemplate.opsForValue().set(key_code,codeStr,60*5,TimeUnit.SECONDS);//验证码有效时间是5分钟

        //发送到用户邮箱
        String content = "你好，欢迎使用miu,验证码是:"+ codeStr +",有效时间5分钟";
        emailService.sendSimpleMail(email,"miu找回密码验证码",content);
        return Global.SUCCESS;

    }
}
