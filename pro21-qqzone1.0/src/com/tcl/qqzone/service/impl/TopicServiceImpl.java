package com.tcl.qqzone.service.impl;

import com.tcl.qqzone.dao.TopicDAO;
import com.tcl.qqzone.pojo.Topic;
import com.tcl.qqzone.pojo.UserBasic;
import com.tcl.qqzone.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }
}
