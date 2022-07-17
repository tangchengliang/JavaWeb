package com.tcl.qqzone.service;

import com.tcl.qqzone.pojo.Reply;

import java.util.List;

/**
 * @author tcl
 * @date 2022-07-14 17:33
 */
public interface ReplyService {
    // 根据topic的ID获取所有关联回复
    List<Reply> getReplyListByTopicId(Integer topicId);

    // 添加回复
    void addReply(Reply reply);

    // 删除指定回复
    void delReply(Integer replyId);
}
