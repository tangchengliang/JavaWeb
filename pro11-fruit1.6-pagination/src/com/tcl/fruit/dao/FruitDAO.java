package com.tcl.fruit.dao;

import com.tcl.fruit.pojo.Fruit;

import java.util.List;

/**
 * 获取所有库存的列表信息
 */
public interface FruitDAO {
    // 获取所有的库存列表信息
    List<Fruit> getFruitList();

    // 获取指定页库存列表信息
    List<Fruit> getFruitList(Integer page);
    // 根据fid获取特定的水果库存信息
    Fruit getFruitByFid(Integer fid);

    // 修改指定的库存记录
    void updateFruit(Fruit fruit);

    // 删除
    void delFruit(Integer fid);

    // 新增
    void addFruit(Fruit fruit);

    // 查询数据库行数
    int getFruitCount();
}
