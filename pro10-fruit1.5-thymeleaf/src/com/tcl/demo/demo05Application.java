package com.tcl.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 演示 Application 保存作用域（demo05 和 demo06）
@WebServlet("/demo05")
public class demo05Application extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 向application 保存作用域和数据
        // ServletContext : Servlet 上下文
        ServletContext application = req.getServletContext();
        application.setAttribute("name", "demo05..application");
//        // 重定向
        resp.sendRedirect("demo06");
        // 服务器 内部转发
//        req.getRequestDispatcher("demo04").forward(req,resp);
    }
}
