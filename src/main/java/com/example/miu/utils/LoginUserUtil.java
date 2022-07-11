package com.example.miu.utils;

import com.alibaba.fastjson.JSON;
import com.example.miu.pojo.table.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginUserUtil {
    private static final String CORE_SESSION_INFO_KEY = "CORE_SESSION_INFO_KEY";
    private static final long SESSION_TIME_OUT = 7200*1000L;
    public static User getLoginUserInfo(){
        try {
            Session session = SecurityUtils.getSubject().getSession();
            String userInfoStr = (String) session.getAttribute(CORE_SESSION_INFO_KEY);
            log.info("LoginUserUtil getLoginUserInfo sessionId:{}, info:{}", session.getId(), userInfoStr);
            return JSON.parseObject(userInfoStr, User.class);
        }catch (Exception e){
            log.error("LoginUserUtil getLoginUserInfo get session error e:", e);
            return null;
        }
    }

    public static void freshLogin(HttpServletRequest request, HttpServletResponse response){
        Session session = SecurityUtils.getSubject().getSession();
        session.setTimeout(SESSION_TIME_OUT * 1000L);
    }

}
