import db.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        boolean isLogin = new DBHelper().login("20185239221", "admin");
        System.out.println(isLogin);
    }
}
