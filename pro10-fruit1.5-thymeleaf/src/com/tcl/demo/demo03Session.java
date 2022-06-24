package com.tcl.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 演示 session 保存作用域（demo03 和 demo04）
@WebServlet("/demo03")
public class demo03Session extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取会话,保存作用域
        HttpSession session = req.getSession();
        session.setAttribute("name", "demo04...session");
//        // 重定向
        resp.sendRedirect("demo04");
        // 服务器 内部转发
//        req.getRequestDispatcher("demo04").forward(req,resp);
    }
}
