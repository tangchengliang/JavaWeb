package com.tcl.qqzone.service;

import com.tcl.qqzone.pojo.Topic;
import com.tcl.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    // 查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic);

    // 根据日志ID查询日志详细信息
    Topic getTopicById(Integer id);

    // 根据id获取指定的topic信息，包含这个topic关联的作者信息
    Topic getTopic(Integer id);
}
