package db;

import jdk.nashorn.internal.scripts.JD;

import java.sql.*;

class DBConnection {
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://62.234.121.94/StuMgr";
    final static String USER = "root";
    final static String PWD = "13945657337xX";

    private Connection conn;
    private Statement stmt;
    private PreparedStatement ptmt;
    private ResultSet rs;

    void Connect(String sql) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    ResultSet getQuery(String sql) {

        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

public class DBHelper extends DBConnection {
}
