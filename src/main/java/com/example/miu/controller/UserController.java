package com.example.miu.controller;


import com.example.miu.Resp.BaseResp;
import com.example.miu.constant.enums.RespEnum;
import com.example.miu.constant.exception.MIUException;
import com.example.miu.pojo.logic.Code;
import com.example.miu.pojo.table.User;
import com.example.miu.pojo.table.UserExample;
import com.example.miu.service.CodeService;
import com.example.miu.service.UserService;
import com.example.miu.utils.FileUtil;
import com.example.miu.utils.Global;
import com.example.miu.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/user")
@Controller
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CodeService codeService;



    @PostMapping("/loginByCode")
    public BaseResp<User> loginByCode(Code code){
        try{
            if (codeService.checkCode(code, Global.REGISTER_LOGIN) != Global.SUCCESS){
                return BaseResp.failed(RespEnum.CODE_ERROR);
            }
            //然后检查邮箱是否存在
            if (userService.ifUserExisted(code.getEmail())){
                //在检查是不是有用户名
                if (userService.ifEmailExistedButNoUsername(code.getEmail())){
                    //没有用户名
                    return BaseResp.failed(RespEnum.USER_NOT_EXIT);
                }
                User user = userService.loginUser(code.getEmail(), code.getCodeValue(),Global.LOGIN_CODE);
                return BaseResp.success(user);
            }else {
                userService.registerUserOfEmail(code.getEmail());
                return BaseResp.failed(RespEnum.USER_NOT_EXIT);
            }
        }catch (MIUException e){
            return BaseResp.failed(RespEnum.getRespEnumByCode(e.getCode()));
        }catch (Exception e){
            return BaseResp.failed(RespEnum.DEFAULT_FAIL);
        }
        //首先检查验证码
    }

    @PostMapping("/updatePassword")
    public ReturnObject<String> updatePassword(Code code,String newPassword){
        //首先检查验证码
        if (codeService.checkCode(code, Global.FIND) != Global.SUCCESS){
            return new ReturnObject<>(Global.CODE_ERROR,"201");
        }
        //验证码正确修改用户密码
        userService.updatePassword(code.getEmail(),newPassword);
        return new ReturnObject<>(Global.SUCCESS,"200");
    }

    @PostMapping("/loginByPassword")
    public ReturnObject<User> loginByPassword(String email,String password){
        if (email == null || password == null){
            return new ReturnObject<>(Global.FAIL,null);
        }
        User user = userService.loginUser(email, password,Global.LOGIN_PASSWORD);
        if (user == null){
            return new ReturnObject<>(Global.FAIL,null);
        }
        return new ReturnObject<>(Global.SUCCESS,user);
    }

    @PostMapping("/register")
    public ReturnObject<User> register(String email,String username,String password){
        User user = userService.registerUserOfUsernameAndPassword(email, username,password);
        if (user == null){
            return new ReturnObject<>(Global.FAIL,null);
        }
        return new ReturnObject<>(Global.SUCCESS,user);
    }

    @PostMapping("/updateInfo")
    public ReturnObject<String> updateInfo(User user,
                                        @RequestParam(value = "image",required = false)MultipartFile image){
        final String basePath = "/home/project/miu/images/user/" + user.getId();
        if (user.getId() == null){
            return new ReturnObject<>(Global.FAIL,"-1");
        }
        if (image != null){
            //先删除文件夹下原有文件
            FileUtil.deleteAllFile(basePath);
            //更新文件
            String newPath = FileUtil.updateFile(basePath,image);

            if (newPath == null){
                //更新失败
                return new ReturnObject<>(Global.FAIL,"-1");
            }
            user.setPhotoPath(newPath);
        }

        userService.updateUser(user);

        return new ReturnObject<>(Global.SUCCESS,"200");
    }

}

