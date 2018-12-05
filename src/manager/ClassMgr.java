package manager;

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
        if (status.isSuperuser && status.isLogin) {
            return status.db.addClass(ClassName);
        }
        return false;
    }
}
