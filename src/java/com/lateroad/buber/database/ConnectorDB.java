package com.lateroad.buber.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
//        ResourceBundle resource = ResourceBundle.getBundle("database");
//
//        Properties properties = new Properties();
//        String url = new String(resource.getString("url"));
//        properties.put("user", resource.getString("user"));
//        properties.put("password", resource.getString("password"));
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/buber?useSSL=false&characterEncoding=UTF-8", "root", "021929");
    }
}
