package com.example.miu.websocket;

import com.alibaba.fastjson.JSON;
import com.example.miu.pojo.logic.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/webSocket/{account}/{channel}")
@Slf4j
public class ChatSocket{
 
    private static int loginCount = 0;
    private static Map<String, ChatSocket> users = new ConcurrentHashMap<>();
    private Session session;
    private String account;
 
    // 收到消息触发事件
    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {
        //JSON数据
        log.info("onMessage:{}",message);
        Map<String,String> map = JSON.parseObject(message, HashMap.class);
        //接收人
        String to = map.get("to");
        //内容
        String info = map.get("info");
        if (to.equals("All")){
            sendMessageAll("ALL:"+info);
        }else{
            sendMessageTo(info,to);
        }
    }
 
    // 打开连接触发事件
    @OnOpen
    public void onOpen(@PathParam("account") String account, @PathParam("channel") String channel,Session session, EndpointConfig config) {
        log.info("onOpen:{}",account);
        this.account = account;
        this.session = session;
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