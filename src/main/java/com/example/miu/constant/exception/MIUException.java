package com.example.miu.constant.exception;

import com.example.miu.constant.enums.RespEnum;
import lombok.Getter;

@Getter
public class MIUException extends RuntimeException{
    Integer code;
    String message;
    public MIUException(Integer code,String message){
        super(message);
        this.message = message;
        this.code = code;
    }
    public MIUException(RespEnum respEnum){
        super(respEnum.getMessage());
        this.message = respEnum.getMessage();
        this.code =respEnum.getCode();
    }
}
