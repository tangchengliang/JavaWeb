package com.tcl.qqzone.service.impl;

import com.tcl.qqzone.dao.TopicDAO;
import com.tcl.qqzone.pojo.Reply;
import com.tcl.qqzone.pojo.Topic;
import com.tcl.qqzone.pojo.UserBasic;
import com.tcl.qqzone.service.ReplyService;
import com.tcl.qqzone.service.TopicService;
import com.tcl.qqzone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null;
    // 此处引用的是ReplyService， 而不是DAO
    private ReplyService replyService;
    private UserBasicService userBasicService;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    // 根据id获取指定的topic信息，包含这个topic关联的作者信息
    @Override
    public Topic getTopic(Integer id){
        Topic topic = topicDAO.getTopic(id);
        UserBasic author = topic.getAuthor();
        author = userBasicService.getUserBasicById(author.getId());
        topic.setAuthor(author);
        return topic;
    }

    @Override
    public Topic getTopicById(Integer id) {
       Topic topic = getTopic(id);
       List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
       topic.setReplyList(replyList);
       return topic;
    }
}
