package com.example.miu.pojo.table;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String photoPath;

    private Boolean gender;

    private String description;

    private Boolean ifShare;
    private String sessionId;

    public String getSessionId(){return sessionId;}
    public void setSessionId(String sessionId){this.sessionId = sessionId;}


    //非数据库字段
    private Integer nowArea;
    private Float x;
    private Float y;
    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNowArea() {
        return nowArea;
    }

    public void setNowArea(Integer nowArea) {
        this.nowArea = nowArea;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath == null ? null : photoPath.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getIfShare() {
        return ifShare;
    }

    public void setIfShare(Boolean ifShare) {
        this.ifShare = ifShare;
    }
}