package com.example.miu.controller;

import java.util.*;

import com.example.miu.pojo.table.Trace;
import com.example.miu.pojo.table.TracingPoint;
import com.example.miu.service.TraceService;
import com.example.miu.utils.Global;
import com.example.miu.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <Description> TraceConrtoller
 *
 * @author 26802
 * @version 1.0
 * @ClassName TraceConrtoller
 * @taskId
 * @see com.example.miu.controller
 */

@RestController
@RequestMapping("/trace")
public class TraceController {

    @Autowired
    private TraceService traceService;


    @GetMapping("/listTraceByUserId")
    public ReturnObject<List<Trace>> getTraceByUserId(Integer userId){
        if (userId != null){
            List<Trace> traceList = traceService.listTraceByUserId(userId);
            return new ReturnObject<>(Global.SUCCESS,traceList);
        }
        return new ReturnObject<>(Global.FAIL,null);
    }

    @GetMapping("/listTraceByAreaId")
    public ReturnObject<List<Trace>> getTraceByAreaId(Integer areaId){
        if (areaId != null){
            List<Trace> traceList = traceService.listTraceByAreaId(areaId);
            return new ReturnObject<>(Global.SUCCESS,traceList);
        }
        return new ReturnObject<>(Global.FAIL,null);
    }

    @PostMapping("/addTrace")
    public ReturnObject<String> addTrace(String jsonList, Trace trace){
        boolean result = traceService.addTraceAndPoint(jsonList, trace);
        if (result){
            return new ReturnObject<>(Global.SUCCESS,"添加成功");
        }
        return new ReturnObject<>(Global.FAIL,"添加失败,轨迹点格式不正确");
    }

}
