package com.tcl.fruit.service;

import com.tcl.fruit.pojo.Fruit;

import java.util.List;

public interface FruitService {
    // 展示指定页面的库存信息
    List<Fruit> getFruitList(String keyword, Integer page);

    // 添加库存记录
    void addFruit(Fruit fruit);
    // 根据id查看库存记录
    Fruit getFruitByFid(Integer fid);
    // 删除特定记录
    void delFruit(Integer fid);
    // 获取总页数
    Integer getPageCount(String keyword);
    // 修改特定库存记录
    void updateFruit(Fruit fruit);
}
