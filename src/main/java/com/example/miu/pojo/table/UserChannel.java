package com.example.miu.pojo.table;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UserChannel implements Serializable {
    String userId;
    String channelId;
}
