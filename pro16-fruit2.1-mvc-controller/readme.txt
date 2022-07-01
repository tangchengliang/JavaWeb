总结：
    1.最初做法，一个请求对应一个Servlet，导致Servl太多
    2.把请求都对应一个FruitServlet，通过operate的值来决定调用FruitServlet中哪一个方法
        switch-case, 如果响应太多----->多个case
    3.解决switch-case
        反射技术：规定operate的值和方法名一致，接受到什么值，就调用什么方法
        问题：每个Servlet中都需要类似的反射方法，继续向上抽取
    4.使用DispatcherServlet
    （1）根据url定位到能够处理这个请求的Controller组件
        1）根据Url中提取的servletPath： /fruit.do -->fruit
        2)根据fruit找到对应的组件：FruitController, 对应于applicationContext.xml中
           <bean id="fruit" class="com.tcl.fruit.controllers.FruitController" />
           通过DOM技术解析xml文件，在中央控制器中形成一个beanMap容器，用来存放所有的Controller组件
        3）根据获取到的operate方法定位到FruitController中需要的方法
    （2）调用Controller组件中的方法
        1)获取参数
            获取即将要调用方法的参数签名信息：    Parameter[] parameters = method.getParameters();
            parameter.getName() 获取参数名称；
            使用Object[] parameterValues 存放对应参数的值
            另外考虑参数类型的问题，parameter.getType() 获取参数类型；需要转化Integer...
        2）执行方法
            找到方法：     Object ReturnObj = method.invoke(controllerBeanObj,parameterValues);
        3）视图处理
             String methodReturnStr = (String)ReturnObj;
             根据ReturnObj返回的值，来处理






1. 在setting->Build->Java Compiler ->添加 -parameters : java虚拟机编译时，会带形参名称， 删除旧的配置文件，然后rebuild，修改之前是args





常见错误：
    1.IllegalArgumentException： argument type mismatch  参数类型不匹配
        page 是Integer， 而parameter获取到是String，
        因此通过if判断，如果获取到的TypeName 是 java.lang.Integer， 则进行强转