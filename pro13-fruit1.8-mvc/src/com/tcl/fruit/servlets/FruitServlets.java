package com.tcl.fruit.servlets;

import com.tcl.fruit.dao.FruitDAO;
import com.tcl.fruit.dao.impl.FruitDAOImpl;
import com.tcl.fruit.myssm.myspringmvc.ViewBaseServlet;
import com.tcl.fruit.myssm.util.StringUtil;
import com.tcl.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/fruit.do")
public class FruitServlets extends ViewBaseServlet {
    FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String operate = req.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index";
        }

        switch (operate){
            case "index":
                index(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "del":
                del(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            default:
                throw new RuntimeException("operate 有误！！");
        }

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        String fcount = req.getParameter("fcount");
        int count = Integer.parseInt(fcount);
        String remark = req.getParameter("remark");
        String fprice = req.getParameter("price");
        int price = Integer.parseInt(fprice);

        Fruit fruit = new Fruit(0, fname, price, count, remark);
        fruitDAO.addFruit(fruit);

        resp.sendRedirect("fruit.do");

    }

    private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);
            // 使用重定向来跳转页面
            resp.sendRedirect("fruit.do");
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);

            Fruit fruit = fruitDAO.getFruitByFid(fid);
            req.setAttribute("fruit", fruit);
            super.processTemplate("edit", req, resp);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 2.获取参数
        String fidStr = req.getParameter("fid");
        int fid = Integer.parseInt(fidStr);
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = req.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");

        // 3.执行更新
        fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        // 4.资源跳转
//        super.processTemplate("index", req, resp);
//        req.getRequestDispatcher("index.html").forward(req,resp); // session数据是更新之前的
        // 此处需要重定向，目的是重新给Servlet发请求，重新获取fruitList，然后覆盖到session中，这样页面上显示的数据才是最新的
        resp.sendRedirect("fruit.do");       // 会更新
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
