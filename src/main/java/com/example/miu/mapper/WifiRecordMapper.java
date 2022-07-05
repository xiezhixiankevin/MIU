package com.example.miu.mapper;

import com.example.miu.pojo.table.WifiRecord;
import com.example.miu.pojo.table.WifiRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WifiRecordMapper {
    int countByExample(WifiRecordExample example);

    int deleteByExample(WifiRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WifiRecord record);

    int insertSelective(WifiRecord record);

    List<WifiRecord> selectByExample(WifiRecordExample example);

    WifiRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WifiRecord record, @Param("example") WifiRecordExample example);

    int updateByExample(@Param("record") WifiRecord record, @Param("example") WifiRecordExample example);

    int updateByPrimaryKeySelective(WifiRecord record);

    int updateByPrimaryKey(WifiRecord record);
}