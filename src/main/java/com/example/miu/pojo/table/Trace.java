package com.example.miu.pojo.table;

import java.io.Serializable;
import java.util.*;

public class Trace implements Serializable {
    private Integer id;

    private String traceName;

    private Integer userId;

    private String description;

    private Integer areaId;

    private String imagePath;

    //非数据库字段
    private List<TracingPoint> pointList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTraceName() {
        return traceName;
    }

    public void setTraceName(String traceName) {
        this.traceName = traceName == null ? null : traceName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public List<TracingPoint> getPointList() {
        return pointList;
    }

    public void setPointList(List<TracingPoint> pointList) {
        this.pointList = pointList;
    }
}