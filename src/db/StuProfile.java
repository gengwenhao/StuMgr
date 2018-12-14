package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StuProfile {
    public String sid;
    public String sno;
    public String name;
    public int gender;
    public int classID;
    public String className;
    public int age;
    public String address;
    public String mobile;
    public String email;

    public static StuProfile getProfile(ResultSet rs) {
        try {
            if (rs.next()) {
                StuProfile profile = new StuProfile();
                profile.sid = rs.getString("sid");
                profile.sno = rs.getString("sno");
                profile.name = rs.getString("sname");
                profile.gender = rs.getInt("sgender");
                profile.className = rs.getString("name");
                profile.age = rs.getInt("age");
                profile.address = rs.getString("address");
                profile.mobile = rs.getString("mobile");
                profile.email = rs.getString("email");
                return profile;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
