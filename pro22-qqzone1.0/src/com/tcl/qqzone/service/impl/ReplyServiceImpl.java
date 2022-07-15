package com.tcl.qqzone.service.impl;

import com.tcl.qqzone.dao.ReplyDAO;
import com.tcl.qqzone.pojo.HostReply;
import com.tcl.qqzone.pojo.Reply;
import com.tcl.qqzone.pojo.Topic;
import com.tcl.qqzone.pojo.UserBasic;
import com.tcl.qqzone.service.HostReplyService;
import com.tcl.qqzone.service.ReplyService;
import com.tcl.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @author tcl
 * @date 2022-07-14 17:34
 */
public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO;
    // 此处引入的是其它的POJO对应的Service接口，而不是DAO口
    // 其他POJO对应的业务逻辑是封装在Service层的，我们需要调用别人的业务逻辑方法，而不是去深入考虑人家内部的细节
    private HostReplyService hostReplyService;      // 这里不是DAO
    private UserBasicService userBasicService;

    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
         // 对ID重新封装一下
        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);
            // 1.将关联的作者设置进去
            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(author);

            // 2.将关联的HostReply设置进去
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }
}
