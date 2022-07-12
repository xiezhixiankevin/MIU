package com.example.miu.mapper;

import com.example.miu.pojo.table.ChatHisMessage;
import com.example.miu.pojo.table.UserChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class ChannelMapperTest {
    @Resource
    private ChannelMapper channelMapper;
    @Resource
    private ChatHisMapper chatHisMapper;

    @Test
    public void Test(){
        List<ChatHisMessage> chatHisMessages =  chatHisMapper.selectByChannel("1",System.currentTimeMillis());
        log.info("{}",chatHisMessages);
    }

}