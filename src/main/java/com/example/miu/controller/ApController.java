package com.example.miu.controller;

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
    public ReturnObject<String> addAp(Ap ap){
        if (apService.addAp(ap) == Global.SUCCESS){
            return new ReturnObject<>(Global.SUCCESS,String.valueOf(Global.SUCCESS));
        }
        return new ReturnObject<>(Global.FAIL,String.valueOf(Global.FAIL));
    }

    @GetMapping("/getAp")
    public ReturnObject<Ap> getAp(String bssid,String ssid){
        Ap ap = null;
        if ((ap = apService.getApBybssidByssid(bssid,ssid)) != null){
            return new ReturnObject<>(Global.SUCCESS,ap);
        }
        return new ReturnObject<>(Global.FAIL,ap);
    }

}
