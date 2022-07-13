package com.example.miu.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.miu.Resp.BaseResp;
import com.example.miu.constant.enums.RespEnum;
import com.example.miu.constant.exception.MIUException;
import com.example.miu.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private ChatService chatService;

    @GetMapping("/channel/join")
    public BaseResp joinChannel(@RequestParam("channel")String channel,@RequestParam("userId")String userId){
        try{
            chatService.joinChannel(channel,userId);
            return BaseResp.success();
        }catch (MIUException e){
            log.error("ChatController joinChannel channel:{},e:",channel,e);
            return BaseResp.failed(RespEnum.getRespEnumByCode(e.getCode()));
        }catch (Exception e){
            log.error("ChatController joinChannel channel:{},e:",channel,e);
            return BaseResp.failed(RespEnum.DEFAULT_FAIL);
        }
    }

    @GetMapping("/channel/leave")
    public BaseResp leaveChannel(@RequestParam("channel")String channel,@RequestParam("userId")String userId){
        try{
            chatService.leaveChannel(channel,userId);
            return BaseResp.success();
        }catch (MIUException e){
            log.error("ChatController joinChannel channel:{},e:",channel,e);
            return BaseResp.failed(RespEnum.getRespEnumByCode(e.getCode()));
        }catch (Exception e){
            log.error("ChatController joinChannel channel:{},e:",channel,e);
            return BaseResp.failed(RespEnum.DEFAULT_FAIL);
        }
    }

    @GetMapping("/channel/chathis")
    public BaseResp getChannelChatHis(@RequestParam("channel")String channel){
        try{
            return BaseResp.success(JSONObject.toJSONString(chatService.getChatHisByChannel(channel)));
        }catch (MIUException e){
            log.error("ChatController joinChannel channel:{},e:",channel,e);
            return BaseResp.failed(RespEnum.getRespEnumByCode(e.getCode()));
        }catch (Exception e){
            log.error("ChatController joinChannel channel:{},e:",channel,e);
            return BaseResp.failed(RespEnum.DEFAULT_FAIL);
        }
    }

    @GetMapping("/channel/users")
    public BaseResp getChannelUserList(@RequestParam("channel")String channel){
        try{
            return BaseResp.success(chatService.getChannelUserList(channel));
        }catch (MIUException e){
            log.error("ChatController joinChannel channel:{},e:",channel,e);
            return BaseResp.failed(RespEnum.getRespEnumByCode(e.getCode()));
        }catch (Exception e){
            log.error("ChatController joinChannel channel:{},e:",channel,e);
            return BaseResp.failed(RespEnum.DEFAULT_FAIL);
        }
    }
}
