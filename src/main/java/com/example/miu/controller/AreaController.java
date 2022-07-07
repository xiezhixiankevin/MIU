package com.example.miu.controller;

import com.example.miu.pojo.table.Area;
import com.example.miu.service.AreaService;
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
 * <Description> AreaController
 *
 * @author 26802
 * @version 1.0
 * @ClassName AreaController
 * @taskId
 * @see com.example.miu.controller
 */
@RequestMapping("/area")
@ResponseBody
@Controller
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping("/addArea")
    public ReturnObject<String> addArea(Area area){
        if (areaService.addArea(area) == Global.SUCCESS){
            return new ReturnObject<>(Global.SUCCESS,String.valueOf(Global.SUCCESS));
        }
        return new ReturnObject<>(Global.FAIL,String.valueOf(Global.FAIL));
    }

    @GetMapping("/listArea")
    public ReturnObject<List<Area>> listArea(Area area){
        return new ReturnObject<>(Global.SUCCESS, areaService.listArea());
    }

}
