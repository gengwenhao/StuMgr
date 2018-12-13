package manager;

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

    // 查看学生分数
    public ScoreProfile[] getScoreList() {
        if (status.isLogin) {
            return ScoreProfile.getProfile(status.db.selectStuScoreBySID(status.userID));
        }
        return null;
    }

    // 查看课程分数
    public ScoreProfile[] getCourseScoreList(int courseID) {
        if (status.isLogin) {
            return ScoreProfile.getProfile(status.db.selectStuScoreByCID(courseID));
        }
        return null;
    }


}
