package com.example.miu.constant.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum RespEnum {
    DEFAULT_FAIL(-1,"系统繁忙,请稍后重试"),
    SUCCESS(200,"请求成功"),
    USER_ID_NULL(1001,"用户对象或id为空");
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
