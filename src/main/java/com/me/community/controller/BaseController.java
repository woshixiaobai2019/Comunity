package com.me.community.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 22:58
 */
public class BaseController {
    public void setAttribute2Session(HttpServletRequest request,String name, Object obj){
        request.getSession().setAttribute(name,obj);
    }
    public Object getAttributeFromSession(HttpServletRequest request,String name){
        return request.getSession().getAttribute(name);
    }
    public String getAttributeFromCookie(HttpServletRequest request,HttpServletResponse response,String name){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)){
                return cookie.getValue();
            }
        }
        return null;
    }
    public void setAttribute2Cookie(HttpServletResponse response,String name,String obj){
        response.addCookie(new Cookie(name,obj));
    }

}
