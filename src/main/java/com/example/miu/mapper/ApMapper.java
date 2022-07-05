package com.example.miu.mapper;

import com.example.miu.pojo.table.Ap;
import com.example.miu.pojo.table.ApExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApMapper {
    int countByExample(ApExample example);

    int deleteByExample(ApExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ap record);

    int insertSelective(Ap record);

    List<Ap> selectByExample(ApExample example);

    Ap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ap record, @Param("example") ApExample example);

    int updateByExample(@Param("record") Ap record, @Param("example") ApExample example);

    int updateByPrimaryKeySelective(Ap record);

    int updateByPrimaryKey(Ap record);
}