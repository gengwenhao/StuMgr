package manager;

public class ClassMgr  {
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
}
