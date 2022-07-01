
7. Thymeleaf - 视图模板技术
    1） 添加thymeleaf的jar包:（1）复制粘贴-->as lib, （2）项目添加依赖，（3）修改artifacts
    2） 新建一个Servlet类ViewBaseServlet
    3） 在web.xml文件中添加配置
       - 配置前缀 view-prefix
       - 配置后缀 view-suffix
    4） 使得我们的Servlet继承ViewBaseServlet

pro10-新的内容：
1.保存作用域
    page：目前不用
    request：一次请求响应范围 （换一个请求就不行）
    session：一次会话范围  (换浏览器，就不行了)
    application：一次应用程序范围有效  （换浏览器，换请求，都行）

2.路径问题
    相对路径
    绝对路径
