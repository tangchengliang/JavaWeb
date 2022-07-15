package com.tcl.qqzone.dao.impl;

import com.tcl.myssm.basedao.BaseDAO;
import com.tcl.qqzone.dao.ReplyDAO;
import com.tcl.qqzone.pojo.Reply;
import com.tcl.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author tcl
 * @date 2022-07-15 14:35
 */
public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Topic topic) {
        return executeQuery("select * from t_reply where topic = ?", topic.getId());
    }

    @Override
    public void addReply(Reply reply) {
        executeUpdate("insert into t_reply values(0,?,?,?,?)", reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic());
    }

    @Override
    public void deleteReply(Integer id) {
        executeUpdate("delete from t_reply where id = ?");
    }
}
