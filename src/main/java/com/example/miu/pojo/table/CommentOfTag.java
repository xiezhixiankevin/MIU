package com.example.miu.pojo.table;

import java.io.Serializable;

public class CommentOfTag implements Serializable {
    private Integer userId;

    private Integer tagId;

    private String comment;

    private Integer recommentWho;

    private String photoPath;

    private String username;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getRecommentWho() {
        return recommentWho;
    }

    public void setRecommentWho(Integer recommentWho) {
        this.recommentWho = recommentWho;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath == null ? null : photoPath.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}