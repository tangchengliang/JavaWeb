package com.tcl.servlets;

import com.tcl.fruit.dao.FruitDAO;
import com.tcl.fruit.dao.impl.FruitDAOImpl;
import com.tcl.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // post 方式下，设置编码，防止中文乱码
        request.setCharacterEncoding("UTF-8");

        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        // 服务器接收数据后打印出来
//        System.out.println("fname = "+fname);
//        System.out.println("price = "+price);
//        System.out.println("fcount = "+fcount);
//        System.out.println("remark = "+remark);

        // 往mysql添加数据
        System.out.println(fname);
        FruitDAO fruitDAO = new FruitDAOImpl();
        boolean flag = fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));
        System.out.println(flag?"添加成功":"添加失败");

    }
}
