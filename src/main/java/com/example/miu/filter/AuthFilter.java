package com.example.miu.filter;

import cn.hutool.json.JSONUtil;
import com.example.miu.Resp.BaseResp;
import com.example.miu.constant.enums.RespEnum;
import com.example.miu.pojo.table.User;
import com.example.miu.utils.LoginUserUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.naming.factory.BeanFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Data
@Slf4j
public class AuthFilter extends AccessControlFilter {
    private static final String NO_AUTH_MSG = "未查询到登陆信息";
    private static final String USER_INFO_MISSED_MSG = "用户信息缺失";
    private static final String NO_PERMISSION_MSG = "您没有相关权限,请联系系统管理员";
    private BeanFactory beanFactory;
    private volatile Boolean inited = false;
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        if(null == servletRequest){
            return false;
        }
        User user = LoginUserUtil.getLoginUserInfo();
        if(user != null){
            LoginUserUtil.freshLogin((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
        }
        return user != null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        PrintWriter out = resp.getWriter();
        resp.setStatus(200);
        resp.setContentType("application/json;charset=utf-8");
        try {
            Session session = SecurityUtils.getSubject().getSession();
            log.info("session:{}",session);
            if(SecurityUtils.getSubject().isAuthenticated()){
                //Session session = SecurityUtils.getSubject().getSession();
                if(session != null && LoginUserUtil.getLoginUserInfo() != null ){
                    out.println(BaseResp.failed(RespEnum.ACCESS_DENIED));
                }
            } else {
                out.println(JSONUtil.toJsonStr(BaseResp.failed(RespEnum.LOGIN_NEEDED)));
            }
        }catch (Exception e){
            log.error("AuthFilter onAccessDenied error e:",e);
            out.println(BaseResp.failed(RespEnum.LOGIN_NEEDED));
        }
        out.flush();
        out.close();
        return false;
    }
}
