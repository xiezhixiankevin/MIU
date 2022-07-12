package com.example.miu.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONString;
import com.alibaba.fastjson.JSON;
import com.example.miu.cache.ChannelUserSetCache;
import com.example.miu.cache.UserChannelCache;
import com.example.miu.constant.enums.RespEnum;
import com.example.miu.constant.exception.MIUException;
import com.example.miu.mapper.AreaMapper;
import com.example.miu.mapper.ChannelMapper;
import com.example.miu.mapper.ChatHisMapper;
import com.example.miu.pojo.logic.MessageDTO;
import com.example.miu.pojo.table.Area;
import com.example.miu.pojo.table.ChatHisMessage;
import com.example.miu.pojo.table.User;
import com.example.miu.pojo.table.UserChannel;
import com.example.miu.service.ChatService;
import com.example.miu.service.UserService;
import com.example.miu.utils.LoginUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    @Resource
    private ChannelUserSetCache channelUserSetCache;
    @Resource
    private UserChannelCache userChannelCache;
    @Resource
    private ChannelMapper channelMapper;
    @Resource
    private ChatHisMapper chatHisMapper;
    @Resource
    private AreaMapper areaMapper;
    @Resource
    private UserService userService;

    @Override
    public void joinChannel(String channel) {
        User user = LoginUserUtil.getLoginUserInfo();
        if(user == null){
            log.error("ChatService joinChannel not in login status");
            throw new MIUException(RespEnum.LOGIN_NEEDED);
        }
        Area area = areaMapper.selectByPrimaryKey(Integer.valueOf(channel));
        if(area == null){
            log.error("ChatService joinChannel channel not exist. channelId:{}", channel);
            throw new MIUException(RespEnum.CHANNEL_NOT_EXIST);
        }
        channelMapper.insertUserChannelInfo(user.getEmail(),channel);
        channelUserSetCache.add(channel, user.getEmail());
        userChannelCache.add(user.getEmail(),channel);
    }

    @Override
    public void leaveChannel(String channel) {
        User user = LoginUserUtil.getLoginUserInfo();
        if(user == null){
            log.error("ChatService joinChannel not in login status");
            throw new MIUException(RespEnum.LOGIN_NEEDED);
        }
        channelMapper.deleteUserChannelInfo(user.getEmail(),channel);
        channelUserSetCache.remove(channel, user.getEmail());
        userChannelCache.remove(user.getEmail(),channel);
    }

    @Override
    public void initOnOpen(String userId) {
        List<UserChannel> userChannelList = channelMapper.selectByUserId(userId);
        if(CollectionUtil.isEmpty(userChannelList)){
            return;
        }
        for(UserChannel userChannel : userChannelList){
            channelUserSetCache.add(userChannel.getChannelId(),userChannel.getUserId());
            userChannelCache.add(userChannel.getUserId(),userChannel.getChannelId());
        }
    }

    @Override
    public void addChatHis(MessageDTO message, String type,String userId) {
        ChatHisMessage chatHisMessage = new ChatHisMessage();
        chatHisMessage.setType(message.getType());
        chatHisMessage.setChannelId(message.getChannel());
        chatHisMessage.setUserId(userId);
        chatHisMessage.setMessage(JSON.toJSONString(message));
        chatHisMapper.insertChatHis(chatHisMessage);
    }

    @Override
    public List<ChatHisMessage> getChatHisByChannel(String channel) {
        return chatHisMapper.selectByChannel(channel,System.currentTimeMillis());
    }

    @Override
    public void onClose(String userId) {
        Set<String> channels =  userChannelCache.values(userId);
        userChannelCache.removeKey(userId);
        for(String channel : channels){
            channelUserSetCache.remove(channel,userId);
        }
    }

    @Override
    public Set<String> getChannelUserIds(String channel) {
        return channelUserSetCache.values(channel);
    }

    public Set<User> getChannelUserList(String channel){
        Set<String> userIds = channelUserSetCache.values(channel);
        Set<User> users = new HashSet<>();

        for(String userId:userIds){
            users.add(userService.getUserByEmail(userId));
        }
        return users;
    }
}
