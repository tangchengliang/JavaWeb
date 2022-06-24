package com.tcl.fruit.servlets;

import com.tcl.fruit.dao.FruitDAO;
import com.tcl.fruit.dao.impl.FruitDAOImpl;
import com.tcl.fruit.myssm.myspringmvc.ViewBaseServlet;
import com.tcl.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

// servlet从3.0版本开始支持 注解 方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList();

        // 保存session作用域
        HttpSession session = req.getSession();
        session.setAttribute("fruitList", fruitList);

        // 此处视图名称：index
        // 那么thymeleaf会将这个 逻辑视图名称，对应到物理视图名称上去
        // 逻辑视图名称： index
        // 物理视图名称： view-prefix+逻辑视图名称+view-suffix
        // 真实的视图名称： /index.html
        super.processTemplate("index",req,resp);
    }
}
