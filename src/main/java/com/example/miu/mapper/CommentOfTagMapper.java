package com.example.miu.mapper;

import com.example.miu.pojo.table.CommentOfTag;
import com.example.miu.pojo.table.CommentOfTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentOfTagMapper {
    int countByExample(CommentOfTagExample example);

    int deleteByExample(CommentOfTagExample example);

    int insert(CommentOfTag record);

    int insertSelective(CommentOfTag record);

    List<CommentOfTag> selectByExample(CommentOfTagExample example);

    int updateByExampleSelective(@Param("record") CommentOfTag record, @Param("example") CommentOfTagExample example);

    int updateByExample(@Param("record") CommentOfTag record, @Param("example") CommentOfTagExample example);
}