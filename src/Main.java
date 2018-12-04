import db.DBHelper;

import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        ResultSet stuList = new DBHelper().getStudentList();
        System.out.println(stuList);
    }
}
