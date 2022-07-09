package com.example.miu.pojo.table.extend;

import com.example.miu.pojo.table.WifiRecord;
import java.util.*;

/**
 * <Description> WifiRecordExtend WifiRecord的扩展类，多了两个属性，便于位置计算
 *
 * @author 26802
 * @version 1.0
 * @ClassName WifiRecordExtend
 * @taskId
 * @see com.example.miu.pojo.table.extend
 */
public class WifiRecordExtend extends WifiRecord {

    private List<Integer> apList;

    private List<Integer> strengthList;

    public WifiRecordExtend(WifiRecord wifiRecord) {
        super(wifiRecord.getAreaId(),wifiRecord.getX(),wifiRecord.getY());
        setId(wifiRecord.getId());
        setAps(wifiRecord.getAps());
        setStrength(wifiRecord.getStrength());

    }

    public List<Integer> getStrengthList() {
        return strengthList;
    }

    public void setStrengthList(List<Integer> strengthList) {
        this.strengthList = strengthList;
    }

    public List<Integer> getApList() {
        return apList;
    }

    public void setApList(List<Integer> apList) {
        this.apList = apList;
    }
}
