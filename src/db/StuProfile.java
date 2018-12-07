package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StuProfile {
    int sid;
    int sno;
    String name;
    int gender;
    int classID;
    String className;
    int age;
    String address;
    String mobile;
    String email;

    static public StuProfile getProfile(ResultSet rs) {
        try {
            rs.next();
            StuProfile profile = new StuProfile();
            profile.sid = rs.getInt("sid");
            profile.sno = rs.getInt("sno");
            profile.name = rs.getString("sname");
            profile.gender = rs.getInt("sgender");
            profile.classID = rs.getInt("class_id");
//            profile.className =
            profile.age = rs.getInt("age");
            profile.address = rs.getString("address");
            profile.mobile = rs.getString("mobile");
            profile.email = rs.getString("email");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
