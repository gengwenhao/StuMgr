package db;

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

    // 批量查询
    ResultSet getQueryMany(String sql1, String sql2) {
        try {
            if (null == stmt || null == conn) Connect();
            stmt.addBatch(sql1);
            return stmt.executeQuery(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 插入删除更新
    int update(String sql) {
        try {
            if (null == stmt || null == conn) Connect();
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

}

public class DBHelper extends DBConnection {
    // 查看学生列表
    public ResultSet getStudentList() {
        return getQuery("SELECT sid, sno, sname, sgender, sbirthday FROM student;");
    }

    // 登录
    public boolean login(String username, String pwd) {
        String sql1 = "SELECT username FROM superuser WHERE username='" + username + "' and password='" + pwd + "';";
        String sql2 = "SELECT sno FROM student WHERE sno='" + username + "' and password='" + pwd + "';";

        ResultSet rs = getQueryMany(sql1, sql2);
        try {
            if (null != rs)
                return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 添加学生信息
    public int addStuProfile(String sno, String name, String password,
                             int gender, int classID) {
        if (null == sno || null == name || null == password)
            return -1;

        String sql = "INSERT INTO student (sno, sname, password, sgender, class_id) values ('" + sno + "','" + name + "','" + password + "'," + gender + "," + classID + ");";
        return update(sql);
    }

    // 通过SID查看学生成绩
    public ResultSet selectStuScorebySID(int sid) {
        String sql = "SELECT * FROM score WHERE student_id=" + sid + ";";

        return getQuery(sql);
    }

    // 查看学生某一科目成绩
    public ResultSet selectStuScorebySIDandCID(int sid, int cid) {
        String sql = "SELECT * FROM score WHERE student_id=" + sid + " and course_id=" + cid + ";";

        return getQuery(sql);
    }

    public boolean updateStuPWD(String newPWD, int sid) {
        String sql = "UPDATE student SET password='" + newPWD + "' WHERE sid=" + sid + ";";

        return update(sql) > 0;
    }
}
