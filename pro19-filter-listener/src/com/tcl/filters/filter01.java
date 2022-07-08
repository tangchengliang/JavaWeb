package com.tcl.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("*.do")
public class filter01 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("A");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
        // 相应回去之后
        System.out.println("A2");
    }

    @Override
    public void destroy() {

    }
}
