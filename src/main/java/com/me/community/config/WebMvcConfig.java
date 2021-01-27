package com.me.community.config;

import com.me.community.config.filters.MyFilter;
import com.me.community.config.interceptors.LoginInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/27 15:49
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/authorizedCallback","/toLogin","/error","/","/index/**","/index.html","/getQuestion","/questionShow","/css/**","/js/**","/fonts/**");
    }
    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean(){
        return new FilterRegistrationBean<>(new CharacterEncodingFilter());
    }
    @Bean
    public FilterRegistrationBean<MyFilter> filterFilterRegistrationBean(){
        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new MyFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
}
