package com.example.miu.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer gender;
    private String email;
    private String photo_path;
    private String description;

    public Integer getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }

    public Integer getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
