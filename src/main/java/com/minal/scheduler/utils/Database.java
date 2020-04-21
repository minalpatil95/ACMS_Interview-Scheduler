package com.minal.scheduler.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final String DATABASE_NAME = "acms";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useSSL=false";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
    }
}
