package com.example.miu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.miu.pojo.table.Ap;
import com.example.miu.service.ApService;
import com.example.miu.utils.Global;
import com.example.miu.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * <Description> ApController
 *
 * @author 26802
 * @version 1.0
 * @ClassName ApController
 * @taskId
 * @see com.example.miu.controller
 */
@RequestMapping("/ap")
@ResponseBody
@Controller
public class ApController {

    @Autowired
    private ApService apService;

    @PostMapping("/addAp")
    public ReturnObject<String> addAp(String aps,Integer areaId){
        ArrayList<Ap> apList  = JSON.parseObject(aps, new TypeReference<ArrayList<Ap>>(){});
        for (Ap ap : apList){
            ap.setAreaId(areaId);
            apService.addAp(ap);
        }
        return new ReturnObject<>(Global.SUCCESS,String.valueOf(Global.SUCCESS));
    }

    @GetMapping("/getAp")
    public ReturnObject<Ap> getAp(String bssid,String ssid){
        Ap ap = null;
        if ((ap = apService.getApBybssidByssid(bssid,ssid)) != null){
            return new ReturnObject<>(Global.SUCCESS,ap);
        }
        return new ReturnObject<>(Global.FAIL,ap);
    }

    @GetMapping("/listApByAreaId")
    public ReturnObject<List<Ap>> listApByAreaId(Integer areaId){
        List<Ap> apList = apService.listApByAreaId(areaId);
        return new ReturnObject<>(Global.SUCCESS,apList);
    }

}
