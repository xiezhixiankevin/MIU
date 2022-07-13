package com.example.miu.pojo.table;


import java.util.*;

public class Comment {
    private Integer id;

    private Integer userId;

    private Integer tagId;

    private String comment;

    private Integer recommentWho;

    private String photoPath;

    private Date createTime;

    private Integer likes;

    //非数据库字段
    private String username;
    private String replyUsername;
    private List<Comment> childList = new ArrayList<>();

    public void addChildComment(Comment comment){
        childList.add(comment);
    }

    public List<Comment> getChildList() {
        return childList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReplyUsername() {
        return replyUsername;
    }

    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setChildList(List<Comment> childList) {
        this.childList = childList;
    }
}