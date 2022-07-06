package com.example.miu.controller;

import com.example.miu.service.CodeService;
import com.example.miu.utils.Global;
import com.example.miu.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <Description> CodeController
 *
 * @author 26802
 * @version 1.0
 * @ClassName CodeController
 * @taskId
 * @see com.example.miu.controller
 */
@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;


    @GetMapping("/sendCode")
    private ReturnObject<String> sendCode(String email,int type){

        if(codeService.sendCode(email,type) == Global.SUCCESS){
               return new ReturnObject<>(Global.SUCCESS,"验证码发送成功，请检查邮件!");
        }

        return new ReturnObject<>(Global.FAIL,"验证码未失效，请勿频繁发送，请检查邮件!");

    }
}
