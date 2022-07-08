package com.tcl.myssm.trans;

import com.tcl.myssm.basedao.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Filter;

public class TransactionManager {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    // 开起事务
    public static void beginTrans() throws SQLException {
       ConnUtil.getConnection().setAutoCommit(false);
    }

    // 提交事务
    public static void commit() throws SQLException {
        Connection conn = ConnUtil.getConnection();
        conn.commit();
        ConnUtil.closeConn();

    }

    // 回滚事务
    public static void rollback() throws SQLException {
        Connection conn = ConnUtil.getConnection();
        conn.rollback();
        ConnUtil.closeConn();
    }
}
