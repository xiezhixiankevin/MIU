package com.example.miu.pojo.table;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class ChatHisMessage implements Serializable {
    String channelId;
    String userId;
    String message;
    String type;
    Date createTime;
}
