1. 设置编码
       request.setCharacterEncoding("UTF-8");
2.servlet继承关系
    （1）继承关系
    javax.servlet.Servlet 接口
        javax.servlet.GenericServlet 抽象类
             javax.servlet.http.HttpServlet 抽象类
    （2）相关方法 init(), service(), destroy()

    (3)服务方法： 当有请求时，service方法会自动相应
                在HttpServlet中会分析请求的方式：到底是get、post、、、
                然后再决定调用的是哪个do方法
                在HttpServlet中，这些do方法默认是405风格
    （4）因此，在新建Servlet时，要考虑请求方法，从而决定重写哪个do方法
3.servlet 生命周期
    (1)生命周期：出生--->死亡: init(), service(), destroy()
    (2)默认情况下：
        第一次接受请求：Servlet会进行实例化（调用构造方法）、初始化（调用init）、然后服务（service）
        从第二次请求开始，每次都是服务
        当容器关闭时，其中的所有Servlet会被销毁
    (3)通过案例发现：
        -Servlet实例Tomcat只会创建一个，所有的请求都是这个实例去响应
        -默认，第一次请求时，Tomcat才会去实例化，初始化，然后服务，好处是什么？提高系统的启动速度，缺点：第一次请求耗时较长
        -因此，如果需要提高系统的启动速度，当前默认情况就是这样。如果需要提高响应速度，我们应该设置Servlet的初始化时机。
    (4)Servlet 请求启动时机
        -默认第一次接受请求时，实例化，初始化
        -通过设置<load-on-startup>来设置servlet启动的先后顺序，启动越靠前，值越小，最小值为0
    (5)Servlet 在容器中：
        -单例：所有请求都是同一个实例去响应
        -线程不安全的：一个线程需要根据这个实例中的某个成员变量值去做逻辑判断。但是在中间某个时机，另一个线程改变了这个成员变量的值，从而导致第一个线程的执行路径发生了变化
        -因此，避免在Servlet中定义成员变量
            如果不得不定义成员变量，那么不要去：①不要去修改成员变量的值 ②不要去根据成员变量的值做一些逻辑判断

4.Http协议
        1） Http 称之为 超文本传输协议
        2） Http是无状态的
        3） Http请求响应包含两个部分：请求和响应
            - 请求：
                 请求包含三个部分： 1.请求行 ； 2.请求消息头 ； 3.请求主体
                  1)请求行包含是三个信息： 1. 请求的方式 ； 2.请求的URL ； 3.请求的协议（一般都是HTTP1.1）
                  2)请求消息头中包含了很多客户端需要告诉服务器的信息，比如：我的浏览器型号、版本、我能接收的内容的类型、我给你发的内容的类型、内容的长度等等
                  3)请求体，三种情况
                            get方式，没有请求体，但是有一个queryString
                            post方式，有请求体，form data
                            json格式，有请求体，request payload
          - 响应：
            响应也包含三本： 1. 响应行 ； 2.响应头 ； 3.响应体
            1)响应行包含三个信息：1.协议 2.响应状态码(200) 3.响应状态(ok)
            2)响应头：包含了服务器的信息；服务器发送给浏览器的信息（内容的媒体类型、编码、内容长度等）
            3)响应体：响应的实际内容（比如请求add.html页面时，响应的内容就是<html><head><body><form....）
5.会话
    1)Http是无状态的
        -无状态：服务器无法判断这两次请求是同一个客户端发过来的，还是不同的客户端发过来的
        -问题：第一次请求是添加商品到购物车，第二次请求是结账；如果这两次请求服务器无法区分是同一个用户的，那么就会导致混乱
        -解决：会话跟踪
    2)会话跟踪
        - 客户端第一次发请求给服务器，服务器获取session，获取不到，则创建新的，然后响应给客户端
        - 下次客户端给服务器发请求时，会把sessionID带给服务器，那么服务器就能获取到了，那么服务器就判断这一次请求和上次某次请求是同一个客户端，从而能够区分开客户端
        - 常用API
              request.getSession() -> 获取当前的会话，没有则创建一个新的会话
              request.getSession(true) -> 效果和不带参数相同
              request.getSession(false) -> 获取当前会话，没有则返回null，不会创建新的

              session.getId() -> 获取sessionID
              session.isNew() -> 判断当前session是否是新的
              session.getMaxInactiveInterval() -> session的非激活间隔时长，默认1800秒
              session.setMaxInactiveInterval()
              session.invalidate() -> 强制性让会话立即失效
    3)session保存作用域
         - session保存作用域是和具体的某一个session对应的
          - 常用的API：
                 void session.setAttribute(k,v)
                 Object session.getAttribute(k)
                 void removeAttribute(k)


6. 服务器内部转发以及客户端重定向
    1)服务器内部转发：: request.getRequestDispatcher("...").forward(request,response);
                    - 一次请求响应的过程，对于客户端而言，内部经过了多少次转发，客户端是不知道的
                    - 地址栏没有变化
    2)客户端重定向： response.sendRedirect("....");
                  - 两次请求响应的过程。客户端肯定知道请求URL有变化
                  - 地址栏有变化

7.Thymeleaf - 视图模板技术





// 200 : 正常响应
// 404 : 找不到资源
// 405 : 请求方式不支持
// 500 : 服务器内部错误