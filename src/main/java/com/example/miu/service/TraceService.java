package com.example.miu.service;

import com.example.miu.pojo.table.Tag;
import com.example.miu.pojo.table.Trace;
import com.example.miu.pojo.table.TracingPoint;

import java.util.List;

/**
 * <Description> TraceService
 *
 * @author 26802
 * @version 1.0
 * @ClassName TraceService
 * @taskId
 * @see com.example.miu.service
 */
public interface TraceService {

    //添加一条轨迹
    int addTrace(Trace trace);

    void addTracingPoint(TracingPoint tracingPoint);

    void updateTrace(Trace trace);


    List<Trace> listTraceByAreaId(Integer id);

    List<Trace> listTraceByUserId(Integer id);

    void initPointList(Trace trace);

    boolean addTraceAndPoint(String jsonList,Trace trace);

}
