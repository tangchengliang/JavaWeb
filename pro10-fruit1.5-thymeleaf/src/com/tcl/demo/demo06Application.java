package com.tcl.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 演示 Application 保存作用域（demo05 和 demo06）
@WebServlet("/demo06")
public class demo06Application extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object name = req.getServletContext().getAttribute("name");
        System.out.println("name = "+name);
    }
}
