package com.tcl.qqzone.service.impl;

import com.tcl.qqzone.dao.HostReplyDAO;
import com.tcl.qqzone.pojo.HostReply;
import com.tcl.qqzone.service.HostReplyService;

/**
 * @author tcl
 * @date 2022-07-14 17:43
 */
public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDAO hostReplyDAO;

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }
}
