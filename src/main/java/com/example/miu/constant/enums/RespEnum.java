package com.example.miu.constant.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum RespEnum {
    DEFAULT_FAIL(-1,"系统繁忙,请稍后重试"),
    SUCCESS(200,"请求成功"),

    ACCESS_DENIED(403,"用户不具备权限"),
    LOGIN_NEEDED(402,"用户未登录"),
    USER_NOT_EXIT(1000,"账号不存在"),
    ACCOUNT_INVALID(1001,"账号或密码错误"),
    USER_ID_NULL(1002,"用户对象或id为空"),
    CODE_ERROR(1003,"验证码错误"),
    CHANNEL_NOT_EXIST(2001,"区域频道不存在");
    private Integer code;
    private String message;

    private RespEnum(Integer code,String message){
        this.code =  code;
        this.message = message;
    }
    public static RespEnum getRespEnumByCode(Integer code){
        return Stream.of(RespEnum.values()).filter(t-> t.getCode().equals(code)).findFirst().orElse(null);
    }
}
