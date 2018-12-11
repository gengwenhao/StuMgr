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

    void Connect() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PWD);
            stmt = conn.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 关闭连接
    public void close() {
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
        return getQuery("SELECT sno, sname FROM student;");
    }

    // 登录
    public int stuLogin(String username, String pwd) {
        String sql = "SELECT sid FROM student WHERE sno='" + username + "' and password='" + pwd + "';";
        ResultSet rs = getQuery(sql);
        try {
            if (null != rs && rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int superUserLogin(String username, String pwd) {
        String sql = "SELECT sid FROM superuser WHERE username='" + username + "' and password='" + pwd + "';";
        ResultSet rs = getQuery(sql);
        try {
            if (null != rs && rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // 添加学生信息
    public boolean addStuProfile(String sno, String name, String password,
                                 int gender, int classID, int age, String address, String email) {
        if (null == sno || null == name || null == password)
            return false;

        String sql = "INSERT INTO student (sno, sname, password, sgender, class_id, age, address, email) values ('" + sno + "','" + name + "','" + password + "'," + gender + "," + classID + "," + age + ",'" + address + "','" + email + "');";
        return update(sql) > 0;
    }

    // 通过SID查看学生成绩
    public ResultSet selectStuScorebySID(int sid) {
        String sql = "SELECT cname,grade FROM score s left join course c on s.course_id=c.cid WHERE student_id=" + sid + ";";
        return getQuery(sql);
    }

    // 查看学生某一科目成绩
    public ResultSet selectStuScorebySIDandCID(int sid, int cid) {
        String sql = "SELECT * FROM score WHERE student_id=" + sid + " and course_id=" + cid + ";";
        return getQuery(sql);
    }

    // 修改学生密码
    public boolean updateStuPWD(String newPWD, int id) {
        String sql = "UPDATE student SET password='" + newPWD + "' WHERE sid=" + id + ";";
        return update(sql) > 0;
    }

    // 查看学生基本信息
    public ResultSet selectStuProfile(int id) {
        String sql = "SELECT sno,sname,sgender,name,age,address,mobile,email FROM student s left join class c on s.class_id=c.cid WHERE sid=" + id + ";";
        return getQuery(sql);
    }

    // 查看学生选课bySID
    public ResultSet selectCourseList(int studentID) {
        String sql = "SELECT cname,cperiod_expriment,credit,ctype FROM course c right join student_course sc on c.cid=sc.cid WHERE sid=" + studentID + ";";
        return getQuery(sql);
    }

    // 修改学生班级 UPDATE student SET class_id=1 WHERE sid=0;
    public boolean updateStuClassBySID(int studentID, int newClassID) {
        String sql = "UPDATE student SET class_id=" + newClassID + " WHERE sid=" + studentID + ";";
        return update(sql) > 0;
    }

    // 修改学生信息
    public boolean updateStuProfile(int studentID, int age, String address, String mobile, String email) {
        String sql = "UPDATE student SET age=" + age + ",address='" + address + "',mobile='" + mobile + "',email='" + email + "'  WHERE sid=" + studentID + ";";
        return update(sql) > 0;
    }

    //查询成绩排名byCID
    //SELECT sid, student_id, grade FROM score WHERE course_id=1 ORDER BY grade desc;
    public ResultSet getScoreRankingByCID(int courseID) {
        String sql = "SELECT sid, student_id, grade FROM score WHERE course_id=" + courseID + " ORDER BY grade desc;";
        return getQuery(sql);
    }

    //查看课程列表
    //SELECT cname, cperiod_expriment, credit, ctype FROM course;
    public ResultSet getCourseList() {
        String sql = "SELECT cname, cperiod_expriment, credit, ctype FROM course;";
        return getQuery(sql);
    }

    //查询课程byCName
    //SELECT cid, cname, cperiod_expriment, credit, ctype FROM course WHERE cname="Python程序设计";
    public ResultSet selectCourseByCName() {
        ResultSet rs = null;

        return rs;
    }

    //添加课程
    //INSERT INTO course (cname, cperiod_expriment, credit, ctype) values(
    //    "Python程序设计", 40, 4, "编程语言"
    //);
    public boolean addCourse(String name, int periodExpriment, int credit, String type) {
        String sql = "INSERT INTO course (cname, cperiod_expriment, credit, ctype) values('" + name + "', " + periodExpriment + ", " + credit + ", '" + type + "');";
        return update(sql) > 0;
    }

    //修改课程信息ByCID
    //UPDATE course SET cname="计算机组成原理", cperiod_expriment=54, credit=3, ctype="计算机组成原理" WHERE cid=4;
    public boolean updateCourseInfo(int courseID, String name, int periodExpriment, int credit, String type) {
        String sql = "UPDATE course SET cname='" + name + "', cperiod_expriment=" + periodExpriment + ", credit=" + credit + ", ctype='" + type + "' WHERE cid=" + courseID + ";";
        return update(sql) > 0;
    }

    //查看班级列表
    //SELECT name FROM class;
    public ResultSet selectClassList() {
        String sql = "SELECT name FROM class";
        return getQuery(sql);
    }

    //添加班级
    //INSERT INTO class (name) values(
    //    "计B181"
    //);
    public boolean addClass(String newClassName) {
        String sql = "INSERT INTO class (name) values('" + newClassName + "');";
        return update(sql) > 0;
    }

    //修改班级名称byCID
    //UPDATE class SET name="软B182" WHERE cid=2;
    public boolean renameClassByCID(int classID, String newClassName) {
        String sql = "UPDATE class SET name='" + newClassName + "' WHERE cid=" + classID + ";";
        return update(sql) > 0;
    }


}
