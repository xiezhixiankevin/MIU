package com.example.miu.config.shiro;

import cn.easyproject.shirorediscache.RedisCacheManager;
import cn.easyproject.shirorediscache.RedisManager;
import cn.easyproject.shirorediscache.RedisSessionDAO;
import com.example.miu.filter.AuthFilter;
import com.example.miu.filter.CustomFormAuthenticationFilter;
import org.apache.naming.factory.BeanFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    private BeanFactory beanFactory;
    @Bean
    @ConditionalOnMissingBean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    //将自己的验证方式加入容器
    @Bean
    public CMSRealMe myShiroRealm() {
        CMSRealMe customRealm = new CMSRealMe();
        return customRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setSessionManager(shiroSessionManager());
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
    @Bean
    public RedisManager redisManager(){
        return new ShiroRedisManager();
    }
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    public DefaultWebSessionManager shiroSessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(redisSessionDAO());
        defaultWebSessionManager.setSessionIdCookie(simpleCookie());
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        return defaultWebSessionManager;
    }
    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        AuthFilter authFilter = new AuthFilter();
        CustomFormAuthenticationFilter customFormAuthenticationFilter =new CustomFormAuthenticationFilter();
        authFilter.setBeanFactory(beanFactory);
        shiroFilterFactoryBean.getFilters().put("fauth",customFormAuthenticationFilter);
        shiroFilterFactoryBean.getFilters().put("authc",authFilter);
        Map<String, String> map = new LinkedHashMap<>();
        //登出
        map.put("/user/**","anon");
        map.put("/code/sendCode","anon");
        map.put("/**","fauth");
        map.put("/logout", "logout");
        //对所有用户认证
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }



    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "customCookie")
    public SimpleCookie simpleCookie(){
        SimpleCookie sc = new SimpleCookie();
        sc.setName("shiro.session");
        sc.setPath("/");
        return sc;
    }
}
