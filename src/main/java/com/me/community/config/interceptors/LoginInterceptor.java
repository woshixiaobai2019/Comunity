package com.me.community.config.interceptors;

import com.me.community.consts.WebConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/27 15:50
 */
@Slf4j

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(WebConst.USER_SESSION_PREFIX) == null) {
            log.debug("拦截未登录用户访问:{}",request.getRequestURI());
            response.getWriter().write("<h1>您还未登录，请先<a href='https://github.com/login/oauth/authorize?client_id=088456cd97b4eb2a2bbf&redirect_uri=http://localhost/authorizedCallback&scope=user'>登录</h1>");
            return false;
        }else{
            return true;
        }
    }
}
