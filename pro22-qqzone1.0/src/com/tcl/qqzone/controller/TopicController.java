package com.tcl.qqzone.controller;

import com.tcl.qqzone.pojo.Reply;
import com.tcl.qqzone.pojo.Topic;
import com.tcl.qqzone.service.ReplyService;
import com.tcl.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author tcl
 * @date 2022-07-14 17:18
 */
public class TopicController {

    private TopicService topicService;
    private ReplyService replyService;

    public String topicDetail(Integer id, HttpSession session){
        Topic topic = topicService.getTopicById(id);

        // replyList内部业务实现
//        List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
//        topic.setReplyList(replyList);

        //保存到session作用域中
        session.setAttribute("topic", topic);

        return "frames/detail";  // 找到路径，不然会报404
    }
}
