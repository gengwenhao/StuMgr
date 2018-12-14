package manager;

import db.ClassProfile;
import db.CourseProfile;

public class ClassMgr {
    private static ClassMgr instance;
    private static MainStatus status;

    public static ClassMgr getSingleton() {
        if (null == status) {
            status = MainStatus.getSingleton();
        }
        if (null == instance) {
            instance = new ClassMgr();
        }
        return instance;
    }

    private ClassMgr() {

    }

    // 添加班级
    public boolean addClass(String ClassName) {
        if (status._IsSuperuser && status.isLogin) {
            return status.db.addClass(ClassName);
        }
        return false;
    }

    // 获取所有班级的信息
    public ClassProfile[] getAllClassList() {
        if (status.isLogin) {
            return ClassProfile.getProfile(status.db.selectClassList());
        }
        return null;
    }
}
