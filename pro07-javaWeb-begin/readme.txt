1.404 : 找不到对应的资源------>注意查看url路径

2.数据库建立fruit表格
    CREATE TABLE fruit2 (fid INT(5) NOT NULL AUTO_INCREMENT,fname VARCHAR(10) DEFAULT NULL,fprice INT(5) DEFAULT NULL,fcount INT(5) DEFAULT NULL, remark VARCHAR(10) DEFAULT NULL,PRIMARY KEY (fid));
    INSERT INTO fruit(fname,fprice,fcount,remark) VALUES("苹果",5,10,"酸"), ("香蕉",3,5,"甜");

3.添加表格数据为null，应该是getParameter时，参数写错了