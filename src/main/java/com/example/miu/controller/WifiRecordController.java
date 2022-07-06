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

}
