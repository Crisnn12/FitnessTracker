package com.fitnesstracker.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconeccion {
    private static final String URL = "jdbc:sqlite:fitness.db";
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                conn = DriverManager.getConnection(URL);
            } catch (SQLException e) {
                System.out.println("Error al conectar: " + e.getMessage());
            }
        }
        return conn;
    }
}
