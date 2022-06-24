package com.tcl.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 演示 request 保存作用域（demo03 和 demo04）
@WebServlet("/demo04")
public class demo04Session extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo04.......");
        Object name = req.getSession().getAttribute("name");
        System.out.println("name = "+name);
    }
}
