package com.example.miu.utils.location;

import com.example.miu.pojo.table.extend.WifiRecordExtend;

import java.util.*;

public class KnnValueSort {
	private Position position;
	private double distance;
	
	public KnnValueSort(float x, float y, double distance) {
		this.distance = distance;
		this.position = new Position(x, y);
	}
	
	public KnnValueSort(WifiRecordExtend userLocation, WifiRecordExtend sampleLocation) {
		List<Integer> userStrength = userLocation.getStrengthList();
		List<Integer> sampleStrength = new KNN().makeApsEqual(userLocation.getApList(), sampleLocation);
		this.position = new Position(sampleLocation);
		this.distance = new KNN().cosine(userStrength, sampleStrength);
		
		System.out.println("("+sampleLocation.getX()+", "+sampleLocation.getY()+"): "+this.distance);
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
}
