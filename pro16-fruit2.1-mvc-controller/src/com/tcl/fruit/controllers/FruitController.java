package com.tcl.fruit.controllers;

import com.tcl.fruit.dao.FruitDAO;
import com.tcl.fruit.dao.impl.FruitDAOImpl;
import com.tcl.fruit.pojo.Fruit;
import com.tcl.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FruitController{

    private FruitDAO fruitDAO = new FruitDAOImpl();

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark){
        //2.获取参数
//        String fidStr = request.getParameter("fid");
//        Integer fid = Integer.parseInt(fidStr);
//        String fname = request.getParameter("fname");
//        String priceStr = request.getParameter("price");
//        int price = Integer.parseInt(priceStr);
//        String fcountStr = request.getParameter("fcount");
//        Integer fcount = Integer.parseInt(fcountStr);
//        String remark = request.getParameter("remark");
        fruitDAO.updateFruit(new Fruit(fid,fname, price ,fcount ,remark));
        return "redirect:fruit.do";
    }

    private String edit(Integer fid, HttpServletRequest request){
        if(fid!=null){
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
            return "edit";
        }
        return "error";
    }

    private String del(Integer fid){
        if(fid!=null){
            fruitDAO.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname, Integer price, Integer fcount, String remark){
        Fruit fruit = new Fruit(0,fname , price , fcount , remark ) ;
        fruitDAO.addFruit(fruit);
        return "redirect:fruit.do";
    }

    private String index(String oper, String keyword, Integer page, HttpServletRequest request){

        HttpSession session = request.getSession() ;
        if(page==null){
            page = 1;
        }
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            page = 1 ;
            if(StringUtil.isEmpty(keyword)){
                keyword = "" ;
            }
            session.setAttribute("keyword",keyword);
        }else{
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj ;
            }else{
                keyword = "" ;
            }
        }
        session.setAttribute("page",page);
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(page, keyword);
        session.setAttribute("fruitList",fruitList);

        int fruitCount = fruitDAO.getFruitCount(keyword);
        int pageCount = (fruitCount+5-1)/5 ;
        session.setAttribute("pageCount",pageCount);
        return "index";
    }
}
