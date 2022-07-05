package com.example.miu.pojo.table;

public class WifiRecord {
    private Integer id;

    private Integer x;

    private Integer y;

    private String strength;

    private String aps;

    private String from;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength == null ? null : strength.trim();
    }

    public String getAps() {
        return aps;
    }

    public void setAps(String aps) {
        this.aps = aps == null ? null : aps.trim();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from == null ? null : from.trim();
    }
}