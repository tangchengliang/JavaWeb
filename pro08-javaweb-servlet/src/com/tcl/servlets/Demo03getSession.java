package com.tcl.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Demo03getSession extends HttpServlet {

    @Override
    protected void service(HttpServletRequest requst, HttpServletResponse resp) throws ServletException, IOException {
        // 获取session会话，
        // 客户端第一次发请求给服务器，服务器获取会话，获取不到，则创建新的，然后响应给客户端
        // 下次客户端给服务器发请求时，会把sessionID带给服务器，从而确定客户端是否是同一个客户端
        HttpSession session = requst.getSession();
        System.out.println("session-ID:"+session);
    }
}
