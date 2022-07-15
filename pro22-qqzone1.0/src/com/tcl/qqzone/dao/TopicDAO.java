package com.tcl.qqzone.dao;

import com.tcl.qqzone.pojo.Topic;
import com.tcl.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicDAO {
    // 获取指定用户的所有日志列表
    List<Topic> getTopicList(UserBasic userBasic);
    // 添加日志
    void addTopic(Topic topic);
    // 删除日志
    void deleteTopic(Integer id);
    // 获取特定日志信息
    Topic getTopic(Integer id);
}
