package com.tcl.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Demo05getSessionArea extends HttpServlet {
    /**
     * 演示从HttpSession中，获取数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object uname = req.getSession().getAttribute("uname");
        System.out.println("uname"+uname);
    }
}
