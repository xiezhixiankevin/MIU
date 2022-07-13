package com.example.miu.mapper;

import com.example.miu.pojo.table.UserChannel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChannelMapper {
    @Insert("insert into user_channel (user_id,channel_id) values(#{userId},#{channel})")
    void insertUserChannelInfo(@Param("userId") String userId,@Param("channel") String channel);

    @Select("select * from user_channel where user_id =#{userId}")
    List<UserChannel> selectByUserId(@Param("userId") String userId);

    @Select("select * from user_channel where channel_id = #{channel}")
    List<UserChannel> selectByChannel(@Param("channel")String channel);

    @Delete("delete from user_channel where user_id = #{userId} and channel_id =# {channel}")
    void deleteUserChannelInfo(@Param("userId")String userId,@Param("channel")String channel);

    @Delete("delete from user_channel where user_id = #{userId}")
    void deleteByUserId(@Param("userId")String userId);

    @Delete("delete from user_channel where channel_id =# {channel}")
    void deleteByChannel(@Param("channel")String channel);
}
