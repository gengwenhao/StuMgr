package manager;

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
}
