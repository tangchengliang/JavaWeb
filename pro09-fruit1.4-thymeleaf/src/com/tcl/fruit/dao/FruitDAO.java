package com.tcl.fruit.dao;

import com.tcl.fruit.pojo.Fruit;

import java.util.List;

/**
 * 获取所有库存的列表信息
 */
public interface FruitDAO {
    List<Fruit> getFruitList();
}
