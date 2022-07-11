package com.tcl.qqzone.dao;

import com.tcl.qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicDAO {
    //根据账号密码获取特定用户信息
    public UserBasic getUserBasic(String loginId, String pwd);

    // 获取指定用户的好友列表
    public List<UserBasic> getUserBasicList(UserBasic userBasic);

    // 根据ID查询userBasic信息
    UserBasic getUserBasicById(Integer id);
}
