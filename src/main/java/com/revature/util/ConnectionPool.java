package com.revature.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        Properties props = getJdbcProperties();
        config.setJdbcUrl(props.getProperty("JDBC_URL"));
        config.setUsername(props.getProperty("JDBC_USERNAME"));
        config.setPassword(props.getProperty("JDBC_PASSWORD"));
        dataSource = new HikariDataSource(config);
    }

    // Restrict instantiation
    private ConnectionPool() {}

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    // Read in the jdbc.properties file, and return the appropriate object
    private static Properties getJdbcProperties() {
        Properties props = new Properties();
        try {
            // Loading the jdbc.properties from our file system
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load [jdbc.properties]");
        }
        return props;
    }
}
