package com.example.miu.service;

import com.example.miu.pojo.table.Ap;
import com.example.miu.pojo.table.Area;
import com.example.miu.pojo.table.WifiRecord;

import java.util.List;

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

    List<WifiRecord> listWifiRecord();



}
