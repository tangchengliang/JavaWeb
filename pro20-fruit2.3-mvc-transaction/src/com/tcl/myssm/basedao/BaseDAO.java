package com.tcl.myssm.basedao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {
    public final String DRIVER = "com.mysql.cj.jdbc.Driver" ;
    public final String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    public final String USER = "root";
    public final String PWD = "123456" ;

    protected Connection conn ;
    protected PreparedStatement psmt ;
    protected ResultSet rs ;

    //T的Class对象
    private Class entityClass ;

    public BaseDAO() throws ClassNotFoundException {
        //getClass() 获取Class对象，当前我们执行的是new FruitDAOImpl() , 创建的是FruitDAOImpl的实例
        //那么子类构造方法内部首先会调用父类（BaseDAO）的无参构造方法
        //因此此处的getClass()会被执行，但是getClass获取的是FruitDAOImpl的Class
        //所以getGenericSuperclass()获取到的是BaseDAO的Class
        Type genericType = getClass().getGenericSuperclass();
        //ParameterizedType 参数化类型
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        //获取到的<T>中的T的真实的类型
        Type actualType = actualTypeArguments[0];
        // 异常往外抛，不要在这里捕获
        entityClass = Class.forName(actualType.getTypeName());

    }

    protected Connection getConn(){
        return ConnUtil.getConnection() ;
    }

    protected void close(ResultSet rs , PreparedStatement psmt , Connection conn){
        // 因为要在业务最后回滚，所有这里的close就位空就行了
        // 全过程都在使用一个connection，因此不能关闭，在最后关闭
    }

    //给预处理命令对象设置参数
    private void setParams(PreparedStatement psmt , Object... params) throws SQLException {
        if(params!=null && params.length>0){
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i+1,params[i]);
            }
        }
    }

    //执行更新，返回影响行数
    protected int executeUpdate(String sql , Object... params) throws SQLException {
        boolean insertFlag = false ;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
        conn = getConn();
        if(insertFlag){
            psmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        }else {
            psmt = conn.prepareStatement(sql);
        }
        setParams(psmt,params);
        int count = psmt.executeUpdate() ;

        if(insertFlag){
            rs = psmt.getGeneratedKeys();
            if(rs.next()){
                return ((Long)rs.getLong(1)).intValue();
            }
        }
        return count ;
    }

    //通过反射技术给obj对象的property属性赋propertyValue值
    private void setValue(Object obj ,  String property , Object propertyValue) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = obj.getClass();
        //获取property这个字符串对应的属性名 ， 比如 "fid"  去找 obj对象中的 fid 属性
        Field field = clazz.getDeclaredField(property);
        if(field!=null){
            field.setAccessible(true);
            field.set(obj,propertyValue);
        }
    }

    //执行复杂查询，返回例如统计结果
    protected Object[] executeComplexQuery(String sql , Object... params) throws SQLException {
        conn = getConn() ;
        psmt = conn.prepareStatement(sql);
        setParams(psmt,params);
        rs = psmt.executeQuery();

        //通过rs可以获取结果集的元数据
        //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

        ResultSetMetaData rsmd = rs.getMetaData();
        //获取结果集的列数
        int columnCount = rsmd.getColumnCount();
        Object[] columnValueArr = new Object[columnCount];
        //6.解析rs
        if(rs.next()){
            for(int i = 0 ; i<columnCount;i++){
                Object columnValue = rs.getObject(i+1);     //33    苹果      5
                columnValueArr[i]=columnValue;
            }
            return columnValueArr ;
        }
        return null ;
    }

    //执行查询，返回单个实体对象
    protected T load(String sql , Object... params) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        conn = getConn() ;
        psmt = conn.prepareStatement(sql);
        setParams(psmt,params);
        rs = psmt.executeQuery();

        //通过rs可以获取结果集的元数据
        //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

        ResultSetMetaData rsmd = rs.getMetaData();
        //获取结果集的列数
        int columnCount = rsmd.getColumnCount();
        //6.解析rs
        if(rs.next()){
            T entity = (T)entityClass.newInstance();

            for(int i = 0 ; i<columnCount;i++){
                String columnName = rsmd.getColumnName(i+1);            //fid   fname   price
                Object columnValue = rs.getObject(i+1);     //33    苹果      5
                setValue(entity,columnName,columnValue);
            }
            return entity ;
        }
        return null ;
    }
    
    //执行查询，返回List
    protected List<T> executeQuery(String sql , Object... params) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        List<T> list = new ArrayList<>();
        conn = getConn() ;
        psmt = conn.prepareStatement(sql);
        setParams(psmt,params);
        rs = psmt.executeQuery();

        //通过rs可以获取结果集的元数据
        //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

        ResultSetMetaData rsmd = rs.getMetaData();
        //获取结果集的列数
        int columnCount = rsmd.getColumnCount();
        //6.解析rs
        while(rs.next()){
            T entity = (T)entityClass.newInstance();

            for(int i = 0 ; i<columnCount;i++){
                String columnName = rsmd.getColumnName(i+1);            //fid   fname   price
                Object columnValue = rs.getObject(i+1);     //33    苹果      5
                setValue(entity,columnName,columnValue);
            }
            list.add(entity);
        }
        return list ;
    }
}
