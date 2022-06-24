package com.tcl.demo;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 演示 request 保存作用域（demo01 和 demo02）
@WebServlet("/demo01")
public class demo01Request extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 向 request,保存作用域
        req.setAttribute("name", "demo01..request");
//        // 重定向
//        resp.sendRedirect("demo02");
        // 服务器 内部转发
        req.getRequestDispatcher("demo02").forward(req,resp);
    }
}
