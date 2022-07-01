package com.tcl.myssm.myspringmvc;

import com.tcl.myssm.io.BeanFactory;
import com.tcl.myssm.io.ClassPathXmlApplicationContext;
import com.tcl.myssm.util.StringUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public DispatcherServlet(){
    }

    public void init() throws ServletException {
        super.init();
        beanFactory = new ClassPathXmlApplicationContext();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //假设url是：  http://localhost:8080/pro15/hello.do
        //那么servletPath是：    /hello.do
        // 解决思路是：
        // 第1步： /hello.do ->   hello   或者  /fruit.do  -> fruit
        // 第2步： hello -> HelloController 或者 fruit -> FruitController
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do") ;
        servletPath = servletPath.substring(0,lastDotIndex);

        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index" ;
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for(Method method: methods){
                if(operate.equals(method.getName())){
                    // 1.统一获取请求参数
                    // 1-1.获取当前方法的参数，返回参数数组, 记得修改java compile 的-parameter
                    Parameter[] parameters = method.getParameters();
                    // 使用parameterValue用来承载参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        // 如果参数名称是 request， response， session ，就不是通过请求中获取参数的方式
                        if("request".equals(parameterName)){
                            parameterValues[i] = request;
                        }else if("response".equals(parameterName)){
                            parameterValues[i] = response;
                        }else if("session".equals(parameterName)){
                            parameterValues[i] = request.getSession();
                        }else {
                            // 从请求中 获取参数
                            String parameterValue = request.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue;
                            // 强转
                            if (parameterObj != null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                                parameterValues[i] = parameterObj;
                            }
                        }
                    }
                    // 2.controller组件中方法调用
                    method.setAccessible(true);
                    Object ReturnObj = method.invoke(controllerBeanObj,parameterValues);

                    // 3. 视图处理
                    String methodReturnStr = (String)ReturnObj;
                    if(methodReturnStr.startsWith("redirect:")){        // 如 redirect：fruit。do
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    }else {
                        super.processTemplate(methodReturnStr, request, response);  // 如： "edit"
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
