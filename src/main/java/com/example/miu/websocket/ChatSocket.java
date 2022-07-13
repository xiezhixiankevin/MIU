package com.example.miu.websocket;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.example.miu.pojo.logic.MessageDTO;
import com.example.miu.pojo.table.User;
import com.example.miu.service.ChatService;
import com.example.miu.service.UserService;
import com.example.miu.service.impl.ChatServiceImpl;
import com.example.miu.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/chatSocket/{account}")
@Slf4j
public class ChatSocket{
//    @Resource
    public static ChatService chatService;
//    @Resource
    public static UserService userService;

    private static int loginCount = 0;
    private static Map<String, ChatSocket> users = new ConcurrentHashMap<>();
    private Session session;
    private String account;
    private User user;
 
    // 收到消息触发事件
    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        log.info("onMessage:{}",message);
        try{
            MessageDTO messageDTO = JSON.parseObject(message,MessageDTO.class);
            String from = messageDTO.getUserId();
            if(!from.equals(account)){
                log.error("onMessage 非本人发送");
                return;
            }
            String channel = messageDTO.getChannel();
            if(StringUtils.isEmpty(channel)){
                log.error("onMessage 发送频道为空");
                return;
            }
            this.sendMessageToChannel(message,channel);
            chatService.addChatHis(messageDTO,messageDTO.getType(),from);
        }catch (Exception e){
            log.error("onMessage 解析错误");
        }
    }
 
    // 打开连接触发事件
    @OnOpen
    public void onOpen(@PathParam("account") String account,Session session, EndpointConfig config) {
        log.info("onOpen:{}",account);
        this.account = account;
        this.session = session;
        chatService.initOnOpen(account);
        this.user = userService.getUserByEmail(account);
        //添加登录用户数量
        addLoginCount();
        users.put(account, this);
        log.info("当前在线人数:"+loginCount);
        log.info("已连接:"+account);
    }
 
    // 关闭连接触发事件
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        log.info("onClose");
        chatService.onClose(account);
        users.remove(account);
        //减少断开连接的用户
        reduceLoginCount();
    }
 
    // 传输消息错误触发事件
    @OnError
    public void onError(Throwable error) {
        log.info("onError:{}",error.getMessage());
    }

    public void sendMessageTo(String message, String To) throws IOException {
        for (ChatSocket item : users.values()) {
            if (item.account.equals(To) )
                item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageToChannel(String message,String channel){
        Set<String> userIds = chatService.getChannelUserIds(channel);
        if(CollectionUtil.isNotEmpty(userIds)){
            for(String userId : userIds){
                ChatSocket chatSocket = users.get(userId);
                if(chatSocket != null && !userId.equals(account)){
                    chatSocket.session.getAsyncRemote().sendText(message);
                }
            }
        }

    }
    public void sendMessageAll(String message) throws IOException {
        for (ChatSocket item : users.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }
 
    public static synchronized void addLoginCount() {
        ChatSocket.loginCount++;
    }

    public static void sendMessageToChannel(MessageDTO messageDTO){

    }
    public static synchronized void reduceLoginCount() {
        ChatSocket.loginCount--;
    }
 
    public static synchronized Map<String, ChatSocket> getUsers() {
        return users;
    }
 
}