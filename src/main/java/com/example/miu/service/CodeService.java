package com.example.miu.service;


import com.example.miu.pojo.logic.Code;

/**
 * <Description> CodeService
 *
 * @author 26802
 * @version 1.0
 * @ClassName CodeService
 * @taskId
 */
public interface CodeService {

    //生成一个验证码保存到redis并发送给用户邮箱(每个用户每天最多申请发送5次验证码)
    int sendCode(String email,int type);

    //检查验证码是否和用户名匹配
    int checkCode(Code code, int type);


    //获取验证码
    String getCode(String email,int type);
}
