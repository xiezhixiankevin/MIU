package com.example.miu.pojo.logic;

import com.example.miu.pojo.table.User;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class ChatHisMessageDTO implements Serializable {
    String channelId;
    User user;
    MessageDTO message;
    String type;
    Date createTime;
}
