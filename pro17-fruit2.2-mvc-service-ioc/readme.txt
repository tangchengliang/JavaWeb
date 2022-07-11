Service - IOC
3.什么事业务层
    1）Model1和Model2
    MVC: Model（模型）, View（视图）, Controller（控制器）
    视图层：用于做数据展示以及和用户交互的一个界面
    控制层：能够接收客户端的请求，具体的业务功能还是需要借助模型组件来完成
    模型层：如pojo/vo(value object)，有业务模型组件，有访问层组件
        1）pojo/vo: 值对象
        2) DAO: 数据访问对象
        3) BO : 业务对象（Business Object）

    2)区分业务对象和数据访问对象：
        1）DAO中的方法都是单精度方法；即一个方法只考虑一个操作，如添加，查询
        2）BO中的方法属于业务方法，实际业务比较复杂，因此业务方法的粒度是比较粗的
        注册这个功能：属于业务功能---->业务方法
        包含多个DAO方法，需要多个DAO方法的组合调用，从而完成注册功能
            -检查用户名是否被注册：DAO-->select
            -用户表新增一个用户：  DAO-->insert
            -删除一个用户：       DAO-->delete
            ...
    3)在库存中添加业务组件

4.IOC
    1)耦合、依赖
        依赖：某某离不开某某，层与层是存在依赖的，也称为耦合
        设计要点：高内聚低耦合
        层内部

        让IOC容器，控制生命周期（从程序移交到IOC，称为控制反转）






1. 在setting->Build->Java Compiler ->添加 -parameters : java虚拟机编译时，会带形参名称， 删除旧的配置文件，然后rebuild，修改之前是args





常见错误：
    1.IllegalArgumentException： argument type mismatch  参数类型不匹配
        page 是Integer， 而parameter获取到是String，
        因此通过if判断，如果获取到的TypeName 是 java.lang.Integer， 则进行强转