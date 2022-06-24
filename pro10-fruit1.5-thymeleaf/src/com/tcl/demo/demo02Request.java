package com.tcl.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 演示 request 保存作用域（demo01 和 demo02）
@WebServlet("/demo02")
public class demo02Request extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取保存的作用域，并打印
//        Object name = req.getSession().getAttribute("name"); // session能获取到name
        System.out.println("demo02..........");
        Object name = req.getAttribute("name");     // 重定向，不能获取到name; 服务器内部转发，能获取
        System.out.println("name = "+name);
    }
}
