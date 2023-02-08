package com.lounwb.meeting.config;

import com.lounwb.meeting.controller.AdminController;
import com.lounwb.meeting.interceptor.AdminVerifyInterceptor;
import com.lounwb.meeting.interceptor.LoginVerifyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginVerifyInterceptor loginVerifyInterceptor = new LoginVerifyInterceptor();
        AdminVerifyInterceptor adminVerifyInterceptor = new AdminVerifyInterceptor();
        registry.addInterceptor(loginVerifyInterceptor).
                addPathPatterns("/**").
                excludePathPatterns("/doRegister","/register","/","/login","/css/**","/js/**","/images/**");
        registry.addInterceptor(adminVerifyInterceptor).
                addPathPatterns("/admin/**");
    }
}
