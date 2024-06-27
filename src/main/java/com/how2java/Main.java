package com.how2java;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Hello world!");
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("初始化驱动成功");
        System.out.println( "Hello World!" );

        DatabaseConnection.link();
    }
}