package manager;

import db.CourseProfile;

import java.sql.ResultSet;

public class CourseMgr {
    private static CourseMgr instance;
    private static MainStatus status;

    public static CourseMgr getSingleton() {
        if (null == status) {
            status = MainStatus.getSingleton();
        }

        if (null == instance) {
            instance = new CourseMgr();
        }
        return instance;
    }

    private CourseMgr() {
    }

    // 添加课程
    public boolean addCourse(String name, int periodExpriment, int credit, String type) {
        if (status.isSuperuser && status.isLogin) {
            return status.db.addCourse(name, periodExpriment, credit, type);
        }
        return false;
    }

    // 修改课程
    public boolean updateCourse(int courseID, String name, int periodExpriment, int credit, String type) {
        if (status.isSuperuser && status.isLogin) {
            return status.db.updateCourseInfo(courseID, name, periodExpriment, credit, type);
        }
        return false;
    }

    // 查看课程列表
    public CourseProfile[] getCourseList() {
        if (status.isLogin) {
            return CourseProfile.getProfile(status.db.selectCourseList(status.userID));
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
