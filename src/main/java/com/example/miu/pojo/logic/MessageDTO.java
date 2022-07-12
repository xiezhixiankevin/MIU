package com.example.miu.pojo.logic;

import com.example.miu.pojo.table.User;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class MessageDTO implements Serializable {
    User from;
    String channel;
    String content;

}
