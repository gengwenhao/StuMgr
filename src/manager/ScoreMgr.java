package manager;

import db.CourseProfile;
import db.ScoreProfile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScoreMgr {
    private static ScoreMgr instance;
    private static MainStatus status;

    public static ScoreMgr getSingleton() {
        if (null == status) {
            status = MainStatus.getSingleton();
        }
        if (null == instance) {
            instance = new ScoreMgr();
        }
        return instance;
    }

    private ScoreMgr() {

    }

    public ScoreProfile[] getScoreList() {
        if (status.isLogin) {
            return ScoreProfile.getProfile(status.db.selectStuScorebySID(status.userID));
        }
        return null;
    }

    // 查看学生课程
    public ResultSet getStuCourse() {
        if (status.isLogin) {
            return status.db.getCourseList();
        }
        return null;
    }

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
