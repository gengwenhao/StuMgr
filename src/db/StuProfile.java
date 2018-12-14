package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public String password;

    @Override
    public String toString() {
        return name;
    }

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

    public static StuProfile[] getProfiles(ResultSet rs) {
        ArrayList<StuProfile> profiles = new ArrayList<StuProfile>();

        try {
            while (rs.next()) {
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
                profiles.add(profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return profiles.toArray(new StuProfile[profiles.size()]);
    }
}
