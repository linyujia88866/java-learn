package com.how2java;

import java.sql.*;
import java.util.Random;

public class DatabaseConnection {
    public static void link() throws ClassNotFoundException, SQLException {
        // 数据库URL，用户名和密码
        String url = "jdbc:mysql://192.168.2.59:3306/test";
        String user = "root";
        String password = "123456";
        Random random = new Random();
        int randomNumber = random.nextInt(200 - 100 + 1) + 100; // 生成100到200之间的随机数
        System.out.println("随机整数：" + randomNumber);


        // 加载并注册JDBC驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 建立数据库连接
//            Connection conn = DriverManager.getConnection(url, user, password);
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.2.59:3306/test?user=root&password=123456");
        Statement stat = conn.createStatement();
//        stat.executeUpdate("insert into students value(randomNumber,'admin',20,'root')");

        // 插入语句
        String insertSQL = "INSERT INTO students (id, name, age, grade) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // 设置参数
            pstmt.setInt(1, randomNumber);
            pstmt.setString(2, "admin");
            pstmt.setInt(3, 20);
            pstmt.setString(4, "大学");

            // 执行插入
            pstmt.executeUpdate();

            System.out.println("Insertion successful");

        } catch (SQLException e) {
            System.out.println("Insertion failed: " + e.getMessage());
        }


        stat.executeUpdate("update students set name = 'sunny' where id = 1");
        stat.executeUpdate("delete from students where id = 2");
        // 操作数据库...
        ResultSet rs = stat.executeQuery("select * from students");
        while (rs.next()){
            int id = rs.getInt("id");
            int age = rs.getInt("age");
            String name = rs.getString("name");
            String grade = rs.getString("grade");
            System.out.println("id="+id+",name="+name+",age="+age+",grade="+grade);
        }

        rs.close();
        stat.close();
        conn.close();
        // 关闭数据库连接
        conn.close();

    }
}