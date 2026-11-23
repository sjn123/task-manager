package com.jani.taskmanager.config;

import com.jani.taskmanager.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jani
 * @data 2026/11/23 22:07
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                // addPathPatterns：拦截所有的 /tasks 开头的请求
                .addPathPatterns("/tasks/**")
                // excludePathPatterns：但是排除登录接口（如果有的话）
                .excludePathPatterns("/login");
    }
}
