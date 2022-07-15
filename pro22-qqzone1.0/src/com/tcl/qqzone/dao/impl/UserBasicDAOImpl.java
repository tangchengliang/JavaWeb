package com.tcl.qqzone.dao.impl;

import com.tcl.myssm.basedao.BaseDAO;
import com.tcl.qqzone.dao.UserBasicDAO;
import com.tcl.qqzone.pojo.UserBasic;

import java.util.List;

public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        return super.load("select * from t_user_basic where loginId = ? and pwd = ?", loginId, pwd);
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        // 涉及多表查询，left join 下去需要学习
        // 下面是简单的实现
        // fid 会报错，因为 UserBasic 没有 fid
        String sql = "select fid as 'id' from t_friend where uid = ?";
        return super.executeQuery(sql, userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return load("select * from t_user_basic where id = ?", id);
    }
}
