package com.example.miu.service.impl;

import com.example.miu.mapper.WifiRecordMapper;
import com.example.miu.pojo.table.WifiRecord;
import com.example.miu.pojo.table.WifiRecordExample;
import com.example.miu.service.WifiRecordService;
import com.example.miu.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public Map<String, Float> calculateLocation(WifiRecord userLocation, List<WifiRecord> areaWifiRecords) {
        return null;
    }
}
