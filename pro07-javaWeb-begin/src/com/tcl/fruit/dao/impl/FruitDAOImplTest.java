package com.tcl.fruit.dao.impl;

import com.tcl.fruit.dao.FruitDAO;
import com.tcl.fruit.pojo.Fruit;

import java.sql.Connection;

import static org.junit.Assert.*;

public class FruitDAOImplTest {

    @org.junit.Test
    public void addFruit() {
        FruitDAO fruitDAO = new FruitDAOImpl();
        boolean flag = fruitDAO.addFruit(new Fruit(0, "apple", 10, 20, "ok"));
    }
}