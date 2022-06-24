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

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return super.load("select * from fruit where fid = ?", fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update fruit set fname = ?, price = ?, fcount = ?, remark = ? where fid = ?";
        super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
    }
}
