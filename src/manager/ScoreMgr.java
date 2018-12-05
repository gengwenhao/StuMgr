package manager;

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

}
