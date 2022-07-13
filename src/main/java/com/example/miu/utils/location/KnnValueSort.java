package com.example.miu.utils.location;

import com.example.miu.pojo.table.extend.WifiRecordExtend;

import java.util.*;

public class KnnValueSort {
	private Position position;
	private double distance;
	private double difference;

	
	public KnnValueSort(WifiRecordExtend userLocation, WifiRecordExtend sampleLocation) {
		List<Integer> userStrength = userLocation.getStrengthList();
		List<Integer> sampleStrength = new KNN().makeApsEqual(userLocation.getApList(), sampleLocation);
		this.position = new Position(sampleLocation);
		this.distance = new KNN().cosine(userStrength, sampleStrength);
		this.difference = new KNN().calDifference(userStrength,sampleStrength);
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

	public double getDifference() {
		return difference;
	}

	public void setDifference(double difference) {
		this.difference = difference;
	}
}
