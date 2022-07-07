package com.example.miu.service;

import com.example.miu.pojo.table.WifiRecord;

import java.util.List;
import java.util.Map;

/**
 * <Description> WifiRecordService
 *
 * @author 26802
 * @version 1.0
 * @ClassName WifiRecordService
 * @taskId
 * @see com.example.miu.service
 */
public interface WifiRecordService {

    int addWifiRecord(WifiRecord wifiRecord);

    int deleteWifiRecord(WifiRecord wifiRecord);

    List<WifiRecord> listWifiRecordByAreaId(Integer areaId);

    /**
     * @param userLocation : 用户所在地的wifi强度信息，包含areaId,aps,strength
     * @param areaWifiRecords : 用户所在区域的数据库中的所有wifi指纹信息
     * @return 返回一个map，包含3个键值对，分别是<"x",数值>,<"y",数值>,<"areaId",区域id>
     * */
    Map<String,Float> calculateLocation(WifiRecord userLocation,List<WifiRecord> areaWifiRecords);


}
