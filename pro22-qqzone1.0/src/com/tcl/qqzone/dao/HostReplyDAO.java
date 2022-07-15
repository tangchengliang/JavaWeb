package com.tcl.qqzone.dao;

import com.tcl.qqzone.pojo.HostReply;

public interface HostReplyDAO {

    // 根据ReplyID获取HostReply实体
    HostReply getHostReplyByReplyId(Integer replyId);
}
