package com.lateroad.buber.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Service class which take care of connection to database.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public final class ConnectorDB {

    private ConnectorDB() {
    }

    /**
     * Creates a connection to database.
     *
     * @return connection.
     */
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("url");
        String password = resource.getString("password");
        String user = resource.getString("user");
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(url, user, password);
    }
}
