package com.example.miu.utils.location;

import com.example.miu.pojo.table.WifiRecord;
import com.example.miu.pojo.table.extend.WifiRecordExtend;

import java.util.*;

public class KNN {
	private int K = 10;
	
	public WifiRecord calculateLocation(WifiRecordExtend userLocation, List<WifiRecordExtend> areaWifiRecord){
		List<KnnValueSort> knnSort = new ArrayList<KnnValueSort>();
		

		for(int i=0; i<areaWifiRecord.size(); i++) {

			if(userLocation.getAreaId() == areaWifiRecord.get(i).getAreaId() ) {
				if(i<this.K) { 

					knnSort.add(new KnnValueSort(userLocation, areaWifiRecord.get(i)));
				}
				else {
					KnnValueSort knnTemp = new KnnValueSort(userLocation, areaWifiRecord.get(i));

					int temp = (knnSort.get(0).getDistance()<knnSort.get(1).getDistance()? 0:1);
					for(int j=2; j<knnSort.size();j++) {
						temp = (knnSort.get(temp).getDistance()<knnSort.get(j).getDistance()? temp:j);
					}
					
					if(knnTemp.getDistance() > knnSort.get(temp).getDistance()) {
						knnSort.set(temp, knnTemp);
					}
				}
			}
		}

		Comparator c = new Comparator<KnnValueSort>() {
			@Override
			public int compare(KnnValueSort o1, KnnValueSort o2) {
				// TODO Auto-generated method stub
				return (o1.getDistance() < o2.getDistance()? 1:-1);
			}
		};
		knnSort.sort(c);
		

		Position loc = new KNN().mostFrequency(knnSort);
		WifiRecord location = new WifiRecord(userLocation.getAreaId(),loc.getX(), loc.getY());
		
		return location;
	}
	

	public List<Integer> makeApsEqual(List<Integer> userAps, WifiRecordExtend sampleRecord) {
		List<Integer> result = new ArrayList<Integer>();
		
		for(int i=0; i<userAps.size(); i++) {

			int pointer = sampleRecord.getApList().indexOf(userAps.get(i));
			result.add(sampleRecord.getStrengthList().get(pointer));
		}
		
		return result;
	}

	private List<Double> shortStrength(List<Integer> strength){
		List<Double> result = new ArrayList<>();
		for (Integer integer : strength) {
			result.add(integer/100.0);
		}
		return result;
	}


	public double cosine(List<Integer> userStrength, List<Integer> sampleStrength) {

		List<Double> userStrengthD = shortStrength(userStrength);
		List<Double> sampleStrengthD = shortStrength(sampleStrength);

		double userModule = 0;

		double sampleModule = 0;

		double numerator = 0;

		double denominator;
		
		for(int i=0; i<userStrengthD.size(); i++) {
			double userTemp = userStrengthD.get(i);
			double sampleTemp = sampleStrengthD.get(i);
			userModule += userTemp*userTemp;
			sampleModule += sampleTemp*sampleTemp;
			numerator += userTemp*sampleTemp;
		}
		
		denominator = Math.sqrt(userModule*sampleModule);
		
		return numerator/denominator;
	}
	

	public Position mostFrequency(List<KnnValueSort> knnSort) {
		Map<Position, Double> map = new HashMap<Position, Double>();

		int i = 0;
		for(KnnValueSort temp: knnSort) {
			i++;
			System.out.println(temp.getPosition().toString()+": "+temp.getDistance());

			if(map.containsKey(temp.getPosition())) {
				map.put(temp.getPosition(), map.get(temp.getPosition())+temp.getDistance());
			}
			else {
				map.put(temp.getPosition(), temp.getDistance());
			}
		}
		
		List<Map.Entry<Position, Double>> list = new ArrayList(map.entrySet());

		Comparator c = new Comparator<Map.Entry<Position, Double>>() {
			@Override
			public int compare(Map.Entry<Position, Double> o1, Map.Entry<Position, Double> o2) {
				// TODO Auto-generated method stub
				return (o1.getValue() < o2.getValue()? 1:-1);
			}
		};
		list.sort(c);
		
		System.out.println("position����Ƶ�Σ�");
		for(Map.Entry<Position, Double> item : list) {
			System.out.println(item.getKey().toString()+": "+item.getValue());
		}
		
		return list.get(0).getKey();
	}
}
