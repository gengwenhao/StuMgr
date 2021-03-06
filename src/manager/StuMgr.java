package manager;

import db.StuProfile;
import db.SuperuserProfile;

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
    public boolean addStudent(String sno, String name, String password, int gender,
                              int classID, int age, String address, String email) {
        return status.db.addStuProfile(sno, name, password, gender, classID, age, address, email);
    }

    // 添加学生
    public boolean addStudent(int classID, StuProfile profile) {
        return status.db.addStuProfile(profile.sno, profile.name,
                profile.password, profile.gender, classID, profile.age, profile.address, profile.email);
    }

    // 查看学生列表
    public StuProfile[] getStudentList() {
        if (status.isLogin) {
            return StuProfile.getProfiles(status.db.getStudentList());
        }
        return null;
    }

    // 修改学生密码
    public boolean changeStudentPassword(String newPassword) {
        if (!status._IsSuperuser && status.isLogin) {
            return status.db.updateStuPWD(newPassword, status.userID);
        }
        return false;
    }

    // 修改学生信息
    public boolean changeStudentProfile(int age, String address, String mobile, String email) {
        if (!status._IsSuperuser && status.isLogin) {
            return status.db.updateStuProfile(status.userID, age, address, mobile, email);
        }
        return false;
    }

    // 修改学生信息
    public boolean changeStudentProfile(StuProfile profile) {
        if (!status._IsSuperuser && status.isLogin && null != profile) {
            return status.db.updateStuProfile(status.userID, profile.age, profile.address, profile.mobile, profile.email);
        }
        return false;
    }

    // 查看学生信息
    public StuProfile getStudentProfile() {
        if (status.isStudent() && status.isLogin) {
            return StuProfile.getProfile(status.db.selectStuProfile(status.userID));
        }
        return null;
    }

    // 查看管理员信息
    public SuperuserProfile getSuperuserProfile() {
        if (status._IsSuperuser && status.isLogin) {
            return SuperuserProfile.getProfile(status.db.selectSuperuserProfile(status.userID));
        }
        return null;
    }

    private StuMgr() {

    }
}
