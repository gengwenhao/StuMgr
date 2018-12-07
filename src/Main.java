import db.DBHelper;
import ui.LoginWin;
import ui.StuHomeWin;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        new LoginWin();
//        new StuHomeWin();
//        new DBHelper().addStuProfile(
//                "2018539229",
//                "gengwenhao213",
//                "admin",
//                1,
//                1,
//                23,
//                "china",
//                "gengwenhao97@126.com"
//        );
//        ResultSet rs = new DBHelper().selectStuProfile(13);
//        rs.next();
//        System.out.println("address:" + rs.getString("address"));
//        System.out.println("email:" + rs.getString("email"));
    }
}
