package com.example.miu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <Description> WebMvcConfig
 *
 * @author 26802
 * @version 1.0
 * @ClassName WebMvcConfig
 * @taskId
 * @see com.example.miu.config
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:///");
    }
}
