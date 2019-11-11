package ru.adkazankov.scienceconference.util;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DbWork {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/conference";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "root";
    private Connection connection;

    private Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }
        return connection;
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
    public Boolean execute(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.execute(sql);
    }
    public Integer executeUpdate(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }

}

