package com.example.miu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.miu.mapper.TraceMapper;
import com.example.miu.mapper.TracingPointMapper;
import com.example.miu.pojo.table.*;
import com.example.miu.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.*;
import java.util.List;

/**
 * <Description> TraceServiceImpl
 *
 * @author 26802
 * @version 1.0
 * @ClassName TraceServiceImpl
 * @taskId
 * @see com.example.miu.service.impl
 */
@Service
public class TraceServiceImpl implements TraceService {

    @Autowired
    private TraceMapper traceMapper;
    @Autowired
    private TracingPointMapper tracingPointMapper;


    @Override
    public int addTrace(Trace trace) {
        traceMapper.insert(trace);
        return trace.getId();
    }

    @Override
    public void addTracingPoint(TracingPoint tracingPoint) {
        tracingPointMapper.insert(tracingPoint);
    }

    @Override
    public void updateTrace(Trace trace) {

    }

    @Override
    public List<Trace> listTraceByAreaId(Integer id) {
        TraceExample traceExample = new TraceExample();
        traceExample.createCriteria().andAreaIdEqualTo(id);
        List<Trace> traceList = traceMapper.selectByExample(traceExample);
        for (Trace trace : traceList) {
            initPointList(trace);
        }
        return traceList;
    }

    @Override
    public List<Trace> listTraceByUserId(Integer id) {
        TraceExample traceExample = new TraceExample();
        traceExample.createCriteria().andUserIdEqualTo(id);
        List<Trace> traceList = traceMapper.selectByExample(traceExample);
        for (Trace trace : traceList) {
            initPointList(trace);
        }
        return traceList;
    }

    @Override
    public void initPointList(Trace trace) {
        Integer traceId = trace.getId();
        TracingPointExample tracingPointExample = new TracingPointExample();
        tracingPointExample.createCriteria().andTraceIdEqualTo(traceId);
        List<TracingPoint> pointList = tracingPointMapper.selectByExample(tracingPointExample);
        trace.setPointList(pointList);
    }

    private static boolean checkPointString(String point){
        String pattern = "\\([0-9]+,[0-9]+,[0-9]+\\)";
        return Pattern.matches(pattern, point);
    }

    @Override
    public boolean addTraceAndPoint(String jsonList,Trace trace) {
        int traceId = addTrace(trace);
        List<TracingPoint> pointList = JSONObject.parseArray(jsonList, TracingPoint.class);
        for (TracingPoint tracingPoint : pointList) {
            tracingPoint.setTraceId(traceId);
            if (!checkPointString(tracingPoint.getPoint())){
                return false;
            }
        }
        for (TracingPoint tracingPoint : pointList) {
            tracingPoint.setTraceId(traceId);
            tracingPointMapper.insert(tracingPoint);
        }
        return true;
    }
}
