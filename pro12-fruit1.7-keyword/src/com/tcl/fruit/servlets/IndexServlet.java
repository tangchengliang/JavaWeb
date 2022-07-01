package com.tcl.fruit.servlets;

import com.tcl.fruit.dao.FruitDAO;
import com.tcl.fruit.dao.impl.FruitDAOImpl;
import com.tcl.fruit.myssm.myspringmvc.ViewBaseServlet;
import com.tcl.fruit.myssm.util.StringUtil;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码格式
        req.setCharacterEncoding("utf-8");
        // 初始页数设置为1
        int page = 1;
        // 获取会话
        HttpSession session = req.getSession();
        // 获取 oper
        String oper = req.getParameter("oper");
        // 如果oper==null，说明通过表单发来数据
        // oper！=null ，说明不是通过表单发来的数据
        String keyword = null;
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            // 点击发来的清求， 此时page应该为1， keyword从req中获取
            keyword = req.getParameter("keyword");
            // 如果keyword为空，则设置为 "" ，否则会被拼接成 %null%
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            // 保存（覆盖） keyword 到session中
            session.setAttribute("keyword", keyword);
        }else {
            // 不是表单发来的数据，如下一页，上一页
            String pageStr = req.getParameter("page");
            if(StringUtil.isNotEmpty(pageStr)){
                // 如果读取到 page， 则强转，否则默认为1
                page = Integer.parseInt(pageStr);
            }
            // 如果不是点击查询按钮，则基于现有的session中报错的keyword进行查询
            // 此时，keyword从session作用域中获取
            Object keywordObj = session.getAttribute("keyword");

            // 同理，如果是第一次查询，且非关键字，要将其设置为 ""
            if(keywordObj == null){
                keyword = "";
            }else {
                keyword = (String)keywordObj;
            }
        }
        // 重新更新并保存page 的作用域
        session.setAttribute("page", page);
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(page, keyword);

        // 保存水果列表的 session作用域
        session.setAttribute("fruitList", fruitList);

        int fruitCount = fruitDAO.getFruitCount(keyword);
        // 分页 默认是5页
        int pageCount = (fruitCount+5-1)/5;
        // 保存count的 session作用域
        session.setAttribute("pageCount", pageCount);

        // 此处视图名称：index
        // 那么thymeleaf会将这个 逻辑视图名称，对应到物理视图名称上去
        // 逻辑视图名称： index
        // 物理视图名称： view-prefix+逻辑视图名称+view-suffix
        // 真实的视图名称： /index.html
        super.processTemplate("index",req,resp);
    }
}
