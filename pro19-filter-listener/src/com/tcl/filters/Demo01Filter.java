package com.tcl.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// 这里的注解，和需要调用的Servlet注解一样，在web.xml文件中也是一样的配置
//@WebFilter("/demo01.do")
@WebFilter("*.do")
public class Demo01Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("helloA");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
        // 相应回去之后
        System.out.println("helloB");
    }

    @Override
    public void destroy() {

    }
}
