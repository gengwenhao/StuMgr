import db.DBHelper;
import manager.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        LoginMgr loginMgr = LoginMgr.getSingleton();
        loginMgr.login("2018539221", "13945657337xX");
        StuMgr stu = StuMgr.getSingleton();

        ResultSet rs = stu.getStudentProfile();
        rs.next();
        System.out.printf("sno:%s sname:%s sgender:%s className:%s",
                rs.getString("sno"),
                rs.getString("sname"),
                rs.getString("sgender"),
                rs.getString("name")
        );

        MainStatus.close();
    }
}
