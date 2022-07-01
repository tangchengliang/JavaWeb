package com.tcl.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

//@WebServlet(urlPatterns = {"/demo01"},
//            initParams = {
//                @WebInitParam(name="hello", value="word"),
//                @WebInitParam(name="uname", value="jim")
//            }
//            )
public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println("intValue = "+ initValue);

        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation = "+contextConfigLocation);
    }
}

// Servlet声明周期： 生成，初始化，职务，销毁
