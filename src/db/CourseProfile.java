package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseProfile {
    public String name;
    public int periodExpriment;
    public int credit;
    public String type;

    public static CourseProfile[] getProfile(ResultSet rs) {
        ArrayList<CourseProfile> li = new ArrayList<CourseProfile>();

        try {

            while (rs.next()) {
                CourseProfile profile = new CourseProfile();
                profile.name = rs.getString("cname");
                profile.periodExpriment = rs.getInt("cperiod_expriment");
                profile.credit = rs.getInt("credit");
                profile.type = rs.getString("ctype");
                li.add(profile);
            }

            return (CourseProfile[]) li.toArray(new CourseProfile[li.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
