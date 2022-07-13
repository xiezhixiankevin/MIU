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
				if (o1.getDistance() < o2.getDistance()){
					return 1;
				}else if (o1.getDistance() == o2.getDistance()){
					return (o1.getDifference() < o2.getDifference()? -1:1);
				}
				return -1;
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
	
	public double calDifference(List<Integer> userStrength,List<Integer> sampleStrength){
		double result = 0.0;
		for (int i = 0; i < userStrength.size(); i++) {
			double var1 = userStrength.get(i)/10.0;
			double var2 = sampleStrength.get(i)/10.0;
			result += (var1-var2)*(var1 - var2);
		}
		return result;
	}


	public Position mostFrequency(List<KnnValueSort> knnSort) {

		Map<String, Integer> map = new HashMap<String, Integer>();

		for(KnnValueSort temp: knnSort) {

			if(map.containsKey(temp.getPosition().getLocation())) {
				map.put(temp.getPosition().getLocation(),map.get(temp.getPosition().getLocation())+1);
			}
			else {
				map.put(temp.getPosition().getLocation(),1);
			}
		}
		//检查是不是所有分类均只有一个
		Map<Integer,Integer> mapCheck = new HashMap<>();
		Set<Map.Entry<String, Integer>> entries = map.entrySet();
		for (Map.Entry<String, Integer> entry : entries) {
			mapCheck.put(entry.getValue(),0);
		}
		if (mapCheck.size()==1 && mapCheck.containsKey(1)){
			return knnSort.get(0).getPosition();
		}

		List<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet());

		Comparator c = new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() <= o2.getValue()){
					return 1;
				}
				return -1;
			}
		};
		list.sort(c);

		String key = list.get(0).getKey();
		for (KnnValueSort knnValueSort : knnSort) {
			if (knnValueSort.getPosition().getLocation().equals(key))
				return knnValueSort.getPosition();
		}

		return null;
	}
}
