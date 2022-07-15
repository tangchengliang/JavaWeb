package com.tcl.qqzone.dao.impl;

import com.tcl.myssm.basedao.BaseDAO;
import com.tcl.qqzone.dao.TopicDAO;
import com.tcl.qqzone.pojo.Topic;
import com.tcl.qqzone.pojo.UserBasic;

import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ?", userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void deleteTopic(Integer id) {

    }

    @Override
    public Topic getTopic(Integer id) {
        return load("select * from t_topic where id = ?", id);
    }
}
