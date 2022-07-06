package com.example.miu.Resp;

import com.example.miu.constant.enums.RespEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResp <T>{
    private Integer code;
    private String message;
    private T data;

    public static BaseResp success(){
        return new BaseResp(RespEnum.SUCCESS.getCode(), RespEnum.SUCCESS.getMessage(), null);
    }
    public static <T> BaseResp<T> success(T data){
        return new BaseResp<>(RespEnum.SUCCESS.getCode(), RespEnum.SUCCESS.getMessage(), null);
    }
    public static BaseResp failed(RespEnum respEnum){
        return new BaseResp(respEnum.getCode(),respEnum.getMessage(),null);
    }
}
