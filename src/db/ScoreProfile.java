package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScoreProfile {
    public String courseName;
    public String grade;

    public static ScoreProfile[] getProfile(ResultSet rs) {
        ArrayList<ScoreProfile> li = new ArrayList<ScoreProfile>();

        try {

            while (rs.next()) {
                ScoreProfile profile = new ScoreProfile();
                profile.courseName = rs.getString("cname");
                profile.grade = rs.getString("grade");
                li.add(profile);
            }

            return (ScoreProfile[]) li.toArray(new ScoreProfile[li.size()]);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
