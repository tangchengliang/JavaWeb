package com.tcl.fruit.service.impl;

import com.tcl.fruit.service.FruitService;
import com.tcl.fruit.dao.FruitDAO;
import com.tcl.fruit.pojo.Fruit;
import com.tcl.myssm.basedao.ConnUtil;

import java.util.List;

public class FruitServiceImpl implements FruitService {
    private FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer page) {
        System.out.println("getFruitList -> "+ ConnUtil.getConnection());
        return fruitDAO.getFruitList(page, keyword);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);

        // 现在模拟回滚报错，下面报错，看上面能否回滚
        Fruit fruit2 = fruitDAO.getFruitByFid(5);
        fruit2.setFcount(102);
        fruitDAO.updateFruit(fruit2);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        System.out.println("getPageCount -> "+ ConnUtil.getConnection());
        int count = fruitDAO.getFruitCount(keyword);
        return (count+5-1)/5 ;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
