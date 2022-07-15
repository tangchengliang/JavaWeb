package com.tcl.myssm.basedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver" ;
    public static final String URL = "jdbc:mysql://localhost:3306/qqzonedb2?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    public static final String USER = "root";
    public static final String PWD = "123456" ;

    public static Connection createConnection(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public static Connection getConnection(){
        Connection conn = threadLocal.get();
        if(conn == null){
            // 需要获取conn
            conn = createConnection();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    // 关闭资源
    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn==null){
            return;
        }
        if(!conn.isClosed()){
            conn.close();
            threadLocal.set(null);
        }
    }
}
