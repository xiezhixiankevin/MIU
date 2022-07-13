package com.example.miu.pojo.logic;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class MessageDTO implements Serializable {
    String userId;
    String channel;
    String content;
    String type;

}
