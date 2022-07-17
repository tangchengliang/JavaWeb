package com.tcl.qqzone.service;

import com.tcl.qqzone.pojo.HostReply;

public interface HostReplyService {
    // 通过回复ID，找到主人回复
    HostReply getHostReplyByReplyId(Integer replyId);

    // 删除主人回复
    void delHostReply(Integer id);
}
