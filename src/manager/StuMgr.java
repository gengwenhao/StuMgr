package manager;

import java.sql.ResultSet;

public class StuMgr {
    private static StuMgr instance;
    private static MainStatus status;

    public static StuMgr getSingleton() {
        if (null == status) {
            status = MainStatus.getSingleton();
        }
        if (null == instance) {
            instance = new StuMgr();
        }
        return instance;
    }

    // 添加学生
    public boolean addStudent(String sno, String name, String password,
                              int gender, int classID) {
        return status.db.addStuProfile(sno, name, password, gender, classID);
    }

    // 查看学生列表
    public ResultSet getStudentList() {
        if (status.isLogin) {
            return status.db.getStudentList();
        }
        return null;
    }

    // 修改学生密码
    public boolean changeStudentPassword(String newPassword) {
        if (!status.isSuperuser && status.isLogin) {
            return status.db.updateStuPWD(newPassword, status.userID);
        }
        return false;
    }

    // 查看学生信息
    public ResultSet getStudentProfile() {
        if (!status.isSuperuser && status.isLogin)
            return status.db.selectStuProfile(status.userID);
        return null;
    }

    private StuMgr() {

    }
}
