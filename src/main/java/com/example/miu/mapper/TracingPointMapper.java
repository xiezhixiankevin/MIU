package com.example.miu.mapper;

import com.example.miu.pojo.table.TracingPoint;
import com.example.miu.pojo.table.TracingPointExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TracingPointMapper {
    int countByExample(TracingPointExample example);

    int deleteByExample(TracingPointExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TracingPoint record);

    int insertSelective(TracingPoint record);

    List<TracingPoint> selectByExample(TracingPointExample example);

    TracingPoint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TracingPoint record, @Param("example") TracingPointExample example);

    int updateByExample(@Param("record") TracingPoint record, @Param("example") TracingPointExample example);

    int updateByPrimaryKeySelective(TracingPoint record);

    int updateByPrimaryKey(TracingPoint record);
}