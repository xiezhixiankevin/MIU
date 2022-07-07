package com.example.miu.service;

import com.example.miu.pojo.table.WifiRecord;
import com.example.miu.pojo.table.extend.WifiRecordExtend;

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

    WifiRecordExtend wifiRecord2Extend(WifiRecord wifiRecord);

    List<WifiRecordExtend> wifiRecordList2Extend(List<WifiRecord> wifiRecordList);

    /**
     * @param userLocation : 用户所在地的wifi强度信息，包含areaId,aps,strength
     * @param areaWifiRecords : 用户所在区域的数据库中的所有wifi指纹信息
     * @return 返回一个WifiRecord,用计算出来的x,y填充它的x，y
     * */
    WifiRecord calculateLocation(WifiRecordExtend userLocation, List<WifiRecordExtend> areaWifiRecords);


}
