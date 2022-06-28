package com.tcl.fruit.servlets;

import com.tcl.fruit.dao.FruitDAO;
import com.tcl.fruit.dao.impl.FruitDAOImpl;
import com.tcl.fruit.myssm.myspringmvc.ViewBaseServlet;
import com.tcl.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fname = req.getParameter("fname");
        String fcount = req.getParameter("fcount");
        int count = Integer.parseInt(fcount);
        String remark = req.getParameter("remark");
        String fprice = req.getParameter("price");
        int price = Integer.parseInt(fprice);

        Fruit fruit = new Fruit(0, fname, price, count, remark);
        fruitDAO.addFruit(fruit);

        resp.sendRedirect("index");

    }
}
