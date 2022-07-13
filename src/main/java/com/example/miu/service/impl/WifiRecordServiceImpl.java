package com.example.miu.service.impl;

import com.example.miu.mapper.WifiRecordMapper;
import com.example.miu.pojo.table.WifiRecord;
import com.example.miu.pojo.table.WifiRecordExample;
import com.example.miu.pojo.table.extend.WifiRecordExtend;
import com.example.miu.service.WifiRecordService;
import com.example.miu.utils.ArrayUtil;
import com.example.miu.utils.Global;
import com.example.miu.utils.location.KNN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description> WifiRecordService
 *
 * @author 26802
 * @version 1.0
 * @ClassName WifiRecordService
 * @taskId
 * @see com.example.miu.service.impl
 */
@Service
public class WifiRecordServiceImpl implements WifiRecordService {

    @Autowired
    private WifiRecordMapper wifiRecordMapper;


    @Override
    public int addWifiRecord(WifiRecord wifiRecord) {
        wifiRecordMapper.insertSelective(wifiRecord);
        return Global.SUCCESS;
    }

    @Override
    public int deleteWifiRecord(WifiRecord wifiRecord) {
        return 0;
    }

    @Override
    public List<WifiRecord> listWifiRecordByAreaId(Integer areaId) {
        WifiRecordExample wifiRecordExample = new WifiRecordExample();
        wifiRecordExample.createCriteria().andAreaIdEqualTo(areaId);
        return wifiRecordMapper.selectByExample(wifiRecordExample);
    }

    @Override
    public WifiRecordExtend wifiRecord2Extend(WifiRecord wifiRecord) {
        WifiRecordExtend wifiRecordExtend = new WifiRecordExtend(wifiRecord);

        List<Integer> var1 = ArrayUtil.str2IntegerList(wifiRecordExtend.getAps());
        List<Integer> var2 = ArrayUtil.str2IntegerList(wifiRecordExtend.getStrength());

        wifiRecordExtend.setApList(var1);
        wifiRecordExtend.setStrengthList(var2);

        return wifiRecordExtend;
    }

    @Override
    public List<WifiRecordExtend> wifiRecordList2Extend(List<WifiRecord> wifiRecordList) {
        List<WifiRecordExtend> wifiRecordExtendList = new ArrayList<>();
        for (WifiRecord wifiRecord : wifiRecordList) {
            wifiRecordExtendList.add(wifiRecord2Extend(wifiRecord));
        }
        return wifiRecordExtendList;
    }

    @Override
    public WifiRecord calculateLocation(WifiRecordExtend userLocation, List<WifiRecordExtend> areaWifiRecords) {
        KNN knn = new KNN();
        return knn.calculateLocation(userLocation,areaWifiRecords);
    }


}
