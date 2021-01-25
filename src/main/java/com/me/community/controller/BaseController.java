package com.me.community.controller;

import com.me.community.config.pojo.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 22:58
 */
@Slf4j
public class BaseController {
    @Autowired
    WebConfig webConfig;
    public void setAttribute2Session(HttpServletRequest request,String name, Object obj){
        request.getSession().setAttribute(name,obj);
    }
    public Object getAttributeFromSession(HttpServletRequest request,String name){
        return request.getSession().getAttribute(name);
    }
    public String getAttributeFromCookie(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        if (cookies ==null){
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)){
                return cookie.getValue();
            }
        }
        return null;
    }
    public void setAttribute2Cookie(HttpServletResponse response,String name,String obj){
        Cookie cookie = new Cookie(name, obj);
        if (obj == null){
            cookie.setMaxAge(0);
        }else{
            cookie.setMaxAge(webConfig.getMaxAge());
        }
        cookie.setPath(webConfig.getPath());
        log.debug("设置请求的cookie:loginToken->{},保存时间:{}s,放问路径:{}",obj,webConfig.getMaxAge(),webConfig.getPath());
        response.addCookie(new Cookie(name,obj));
    }

}
