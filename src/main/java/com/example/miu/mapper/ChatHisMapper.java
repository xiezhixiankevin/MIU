package com.example.miu.mapper;

import com.example.miu.pojo.table.ChatHisMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatHisMapper {
    @Insert("insert into chat_his (channel_id,user_id,message,type) values(#{chatHis.channelId},#{chatHis.userId},#{chatHis.message},#{chatHis.type})")
    void insertChatHis(@Param("chatHis")ChatHisMessage chatHisMessage);

    @Select("select * from chat_his where channel_id =#{channel} and type != 'SYS' and #{currentTime}-create_time<86400000")
    List<ChatHisMessage> selectByChannel(@Param("channel")String channel,@Param("currentTime")Long timestamp);
}
