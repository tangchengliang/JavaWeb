package com.tcl.qqzone.dao.impl;

import com.tcl.myssm.basedao.BaseDAO;
import com.tcl.qqzone.dao.HostReplyDAO;
import com.tcl.qqzone.pojo.HostReply;

/**
 * @author tcl
 * @date 2022-07-14 17:46
 */
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return load("select * from t_host_reply where reply = ?", replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        super.executeUpdate("delete from t_host where id = ", id);
    }
}
