import manager.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        LoginMgr loginMgr = LoginMgr.getSingleton();
        loginMgr.login("2018539221", "2134");
        StuMgr stuMgr = StuMgr.getSingleton();
        System.out.println(stuMgr.changeStudentPassword("13945657337xX"));

        MainStatus.close();
    }
}
