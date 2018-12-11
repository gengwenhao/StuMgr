package manager;

import db.CourseProfile;
import db.ScoreProfile;

import java.sql.ResultSet;

public class ScoreMgr  {
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

}
