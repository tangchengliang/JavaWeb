package com.tcl.myssm.filters;

import com.tcl.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            // 开起事务
            TransactionManager.beginTrans();
            // 放行
            filterChain.doFilter(servletRequest, servletResponse);
            // 放行之后的代码没有错误，就提交
            TransactionManager.commit();
        }catch (Exception e){
            e.printStackTrace();
            try {
                // 放行之后的代码有错误，就回滚
                TransactionManager.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
