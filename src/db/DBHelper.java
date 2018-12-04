package db;

import jdk.nashorn.internal.scripts.JD;

import java.sql.*;

class DBConnection {
    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://62.234.121.94/stuMgr" + "?useSSL=false&characterEncoding=utf8";
    private final static String USER = "root";
    private final static String PWD = "13945657337xX";

    private Connection conn;
    private Statement stmt;
    private PreparedStatement ptmt;
    private ResultSet rs;

    private void Connect() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PWD);
            stmt = conn.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 关闭连接
    void Close() {
        try {
            if (null != conn) {
                conn.close();
            }
            if (null != stmt) {
                stmt.close();
            }
            if (null != rs) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询
    ResultSet getQuery(String sql) {
        try {
            if (null == stmt || null == conn) Connect();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

public class DBHelper extends DBConnection {
    public ResultSet getStudentList() {
        ResultSet rs = null;
        rs = getQuery("SELECT sid, sno, sname, sgender, sbirthday FROM student;");

        return rs;
    }
}
