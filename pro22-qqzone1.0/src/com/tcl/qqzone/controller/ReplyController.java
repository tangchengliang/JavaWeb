package com.tcl.qqzone.controller;

import com.tcl.qqzone.pojo.Reply;
import com.tcl.qqzone.pojo.Topic;
import com.tcl.qqzone.pojo.UserBasic;
import com.tcl.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author tcl
 * @date 2022-07-15 17:30
 */
public class ReplyController {

    private ReplyService replyService;

    /**
     * 
     * @author tcl
     * @date 2022/7/15 17:31
     * @param content 回复内容
     * @param session 需要获取作者，即当前登录的人，需要从session中获取
     * @param topicId 回复的topicId，在页面隐藏域中获取
     * @return java.lang.String
     */
    public String addReply(String content, Integer topicId, HttpSession session){
        // 1. 在 Topic中添加带参构造方法
        // 2. 通过session获取作者
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(content, new Date(), author, new Topic(topicId));
        // 3.添加回复，Reply方法中没有add方法，因此需要添加
        replyService.addReply(reply);
        // 4.要查询查询detail.html, 不能直接访问detail.html页面，因为数据未更新
        // 因此，重定向
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }

    public String delReply(Integer replyId, Integer topicId){
        replyService.delReply(replyId);

        // 删除完之后，到详情页面
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }
}
