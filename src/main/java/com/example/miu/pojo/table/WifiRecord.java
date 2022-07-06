package com.example.miu.pojo.table;

public class WifiRecord {
    private Integer id;

    private Integer areaId;

    private Float x;

    private Float y;

    private String aps;

    private String strength;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
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

    public String getAps() {
        return aps;
    }

    public void setAps(String aps) {
        this.aps = aps == null ? null : aps.trim();
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength == null ? null : strength.trim();
    }
}