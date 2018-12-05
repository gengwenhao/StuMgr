package manager;

import java.sql.ResultSet;

public class StuMgr {
    private static StuMgr instance;
    public static MainStatus status;

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
        return status.db.getStudentList();
    }

    // 修改学生密码
    public boolean changeStudentPassword(String newPassword) {
        if (!status.isSuperuser) {
            return status.db.updateStuPWD(newPassword, status.userID);
        }

        return false;
    }

    private StuMgr() {

    }
}
