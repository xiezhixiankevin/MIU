package com.example.miu.service;

import com.example.miu.pojo.table.User;

import java.util.List;

/**
 * <Description> UserService
 *
 * @author 26802
 * @version 1.0
 * @ClassName UserService
 * @taskId
 * @see com.example.miu.service
 */
public interface UserService {

    //注册邮箱
    boolean registerUserOfEmail(String email);

    //注册邮箱
    //此方法需要把用户信息保存在redis里
    User registerUserOfUsernameAndPassword(String email,String username,String password);

    /*登录部分*/

    /*
     * 同样,登陆成功返回用户实体(包含从数据库中查到的所有字段),否则返回null
     * xzx
     * type:true 密码登录  false:验证码登录
     * 此方法需要把用户信息保存在redis里
     * */
    User loginUser(String email, String text,boolean type);

    /*找回密码部分
     *
     * */
    String retrievePassword(String username); //根据用户名查询密码并返回，调用此方法的前提是验证码输入正确

    /*通用部分*/
    List<User> listUser(); //获取数据库中的所有用户 xzx
    List<User> listUserSelective(User user); //xzx
    User getUserByUserName(String username); //xzx
    User getUserByEmail(String email); //xzx


    boolean ifUserExisted(String email); //wkx
    int deleteUser(String email); //wkx
    int updateUser(User user); //wkx



}
