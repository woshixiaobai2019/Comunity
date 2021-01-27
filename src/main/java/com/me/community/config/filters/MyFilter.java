package com.me.community.config.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/27 16:38
 */
@Slf4j
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      response.setCharacterEncoding("UTF-8");
      chain.doFilter(request,response);
    }
}
