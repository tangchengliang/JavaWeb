package com.tcl.fruit.dao.impl;

import com.tcl.fruit.dao.FruitDAO;
import com.tcl.fruit.myssm.basedao.BaseDAO;
import com.tcl.fruit.pojo.Fruit;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from fruit");
    }
}
