package com.example.miu.pojo.logic;

import com.example.miu.pojo.table.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
public class ShiroUserInfo implements Serializable {
    String name;
    String password;

    public ShiroUserInfo(User user){
        this.name= user.getEmail();
        this.password = user.getPassword();
    }

}
