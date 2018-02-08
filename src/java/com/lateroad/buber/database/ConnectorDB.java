package com.lateroad.buber.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        
        Properties properties = new Properties();
        String url = resource.getString("url");
        properties.put("user", resource.getString("user"));
        properties.put("password", resource.getString("password"));
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection(url, properties);
    }
}
