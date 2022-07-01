package com.tcl.fruit.controllers;

import com.tcl.fruit.service.FruitService;
import com.tcl.fruit.pojo.Fruit;
import com.tcl.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FruitController{

    private FruitService fruitService= null;

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark){
        fruitService.updateFruit(new Fruit(fid,fname, price ,fcount ,remark));
        return "redirect:fruit.do";
    }

    private String edit(Integer fid, HttpServletRequest request){
        if(fid!=null){
            Fruit fruit = fruitService.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
            return "edit";
        }
        return "error";
    }

    private String del(Integer fid){
        if(fid!=null){
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname, Integer price, Integer fcount, String remark){
        Fruit fruit = new Fruit(0,fname , price , fcount , remark ) ;
        fruitService.addFruit(fruit);
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
        List<Fruit> fruitList = fruitService.getFruitList(keyword, page);
        session.setAttribute("fruitList",fruitList);

        int pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("pageCount",pageCount);
        return "index";
    }
}
