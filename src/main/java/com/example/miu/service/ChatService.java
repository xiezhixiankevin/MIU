package com.example.miu.service;

import com.example.miu.pojo.logic.MessageDTO;
import com.example.miu.pojo.table.ChatHisMessage;

import java.util.List;
import java.util.Set;

public interface ChatService {
    void joinChannel(String channel);
    void leaveChannel(String channel);
    void initOnOpen(String userId);
    void addChatHis(MessageDTO message, String type,String userId);
    List<ChatHisMessage> getChatHisByChannel(String channel);
    void onClose(String userId);

    Set<String> getChannelUserIds(String channel);

}
