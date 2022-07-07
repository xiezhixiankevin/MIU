package com.example.miu.controller;

import com.example.miu.pojo.table.WifiRecord;
import com.example.miu.service.WifiRecordService;
import com.example.miu.utils.Global;
import com.example.miu.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <Description> WifiRecordController
 *
 * @author 26802
 * @version 1.0
 * @ClassName WifiRecordController
 * @taskId
 * @see com.example.miu.controller
 */
@RequestMapping("/wifiRecord")
@ResponseBody
@Controller
public class WifiRecordController {

    @Autowired
    private WifiRecordService wifiRecordService;

    @PostMapping("/addWifiRecord")
    @ResponseBody
    public ReturnObject<String> addWifiRecord(WifiRecord wifiRecord){
        if (wifiRecordService.addWifiRecord(wifiRecord) == Global.SUCCESS){
            return new ReturnObject<>(Global.SUCCESS,String.valueOf(Global.SUCCESS));
        }
        return new ReturnObject<>(Global.FAIL,String.valueOf(Global.FAIL));
    }

    @PostMapping("/getLocation")
    @ResponseBody
    public ReturnObject<Map<String,Float>> getLocation(WifiRecord wifiRecord){
        if (checkWifiRecord(wifiRecord)){
            Map<String, Float> map = wifiRecordService.calculateLocation(wifiRecord, wifiRecordService.listWifiRecordByAreaId(wifiRecord.getAreaId()));
            return new ReturnObject<>(Global.SUCCESS,map);
        }
        return new ReturnObject<>(Global.FAIL,null);
    }

    private boolean checkWifiRecord(WifiRecord wifiRecord){
        if (wifiRecord == null)
            return false;
        if (wifiRecord.getAreaId() == null)
            return false;
        if (wifiRecord.getAps() == null)
            return false;
        if (wifiRecord.getStrength() == null)
            return false;
        return true;
    }

}
