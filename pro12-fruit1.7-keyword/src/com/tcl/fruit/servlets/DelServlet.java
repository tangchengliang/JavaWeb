package com.tcl.fruit.servlets;

import com.tcl.fruit.dao.FruitDAO;
import com.tcl.fruit.dao.impl.FruitDAOImpl;
import com.tcl.fruit.myssm.myspringmvc.ViewBaseServlet;
import com.tcl.fruit.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);
            // 使用重定向来跳转页面
            resp.sendRedirect("index");
        }
    }
}
