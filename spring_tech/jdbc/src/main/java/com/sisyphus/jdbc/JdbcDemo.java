package com.sisyphus.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcDemo {
    public static void main(String[] args) throws Exception{
        //注册驱动
//        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); //耦合
        Class.forName("com.mysql.cj.jdbc.Driver"); //字符串写入，编译期不出错，一定程度解耦合
        //获取连接，连接 mysql 的时候注意指定 useSSL 为 false，否则不符合 mysql 对 SSL 的要求会报错
        Connection conn = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/spring_tech?useSSL=false", "root", "Sisyphus12");
        //获取操作数据库的预处理对象
        PreparedStatement pstm = conn.prepareStatement("SELECT * FROM account");
        //执行 sql 语句，得到结果集
        ResultSet rs = pstm.executeQuery();
        //遍历结果集
        while(rs.next()) {
            System.out.println(rs.getString("name"));
        }
        //释放资源
        rs.close();
        pstm.close();
        conn.close();
    }
}
