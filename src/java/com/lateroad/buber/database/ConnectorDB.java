package com.lateroad.buber.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {

    public static Connection getConnection() throws SQLException {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String password = resource.getString("password");
        System.out.println(url);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/buber?useSSL=false&characterEncoding=UTF-8", "root", "021929");
    }
}
