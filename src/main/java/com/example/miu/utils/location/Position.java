package com.example.miu.utils.location;

import com.example.miu.pojo.table.extend.WifiRecordExtend;

import java.util.Objects;

public class Position {
	private float x;
	private float y;
	private int hashCode;
	
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
		this.hashCode = Objects.hash(x,y);
	}
	
	public Position(WifiRecordExtend areaWifiRecord) {
		this.x = areaWifiRecord.getX();
		this.y = areaWifiRecord.getY();
		this.hashCode = Objects.hash(x,y);
	}


	@Override 
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Position that = (Position) o;
		return x == that.x && y ==that.y;
	}
	
	@Override 
	public int hashCode() {
		return this.hashCode; 
	}
	
	public String toString() {
		return "("+x+", "+y+")";
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getX() {
		return x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getY() {
		return y;
	}
}
