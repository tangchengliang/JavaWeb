package com.tcl.fruit.dao.impl;

import com.tcl.fruit.dao.FruitDAO;
import com.tcl.myssm.basedao.BaseDAO;
import com.tcl.fruit.pojo.Fruit;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from fruit");
    }

    @Override
    public List<Fruit> getFruitList(Integer page) {
        String sql = "select * from fruit limit ? , 5";
        return super.executeQuery(sql, (page-1)*5);
    }

    @Override
    public List<Fruit> getFruitList(Integer page, String keyword) {
        String sql = "select * from fruit where fname like ? or remark like ? limit ? , 5";
        return super.executeQuery(sql,"%"+keyword+"%", "%"+keyword+"%", (page-1)*5);
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

    @Override
    public void delFruit(Integer fid) {
        String sql = "delete from fruit where fid = ?";
        super.executeUpdate(sql, fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into fruit(fname, price, fcount, remark) values(?,?,?,?)";
        super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
    }

    @Override
    public int getFruitCount() {
        return ((Long) super.executeComplexQuery("select count(*) from fruit")[0]).intValue();
    }

    @Override
    public int getFruitCount(String keyword) {
        return ((Long) super.executeComplexQuery("select count(*) from fruit where fname like ? or remark like ?", "%"+keyword+"%", "%"+keyword+"%")[0]).intValue();
    }
}
