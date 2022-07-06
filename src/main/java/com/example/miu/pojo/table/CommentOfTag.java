package com.example.miu.pojo.table;

public class CommentOfTag {
    private Integer userId;

    private Integer tagId;

    private String comment;

    private Integer recommentWho;

    private String photoPath;

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
}