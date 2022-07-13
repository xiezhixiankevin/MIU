package com.example.miu.config;

import com.example.miu.service.ChatService;
import com.example.miu.service.UserService;
import com.example.miu.websocket.ChatSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket
public class WebSocketConf {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


    @Resource
    public void setChatService(ChatService chatService){
        ChatSocket.chatService = chatService;
    }

    @Resource
    public void setUserService(UserService userService){
        ChatSocket.userService = userService;
    }

}
