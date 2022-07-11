package com.example.miu.filter;

import cn.hutool.json.JSONUtil;
import com.example.miu.pojo.table.User;
import com.example.miu.service.UserService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final String CORE_SESSION_INFO_KEY = "CORE_SESSION_INFO_KEY";
    @Resource
    private UserService userService;
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        //获取已登录的用户信息
        String userName = (String) subject.getPrincipal();
        //获取session
        User user = userService.getUserByEmail(userName);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        //把用户信息保存到session
        session.setAttribute("CORE_SESSION_INFO_KEY", JSONUtil.toJsonStr(user));
        return super.onLoginSuccess(token, subject, request, response);
    }
}
