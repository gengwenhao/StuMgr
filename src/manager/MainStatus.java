package manager;

import db.DBHelper;

public class MainStatus {
    private static MainStatus instance;
    boolean isLogin;
    boolean isSuperuser;
    int userID;
    DBHelper db;
    String stuClassName;

    public static MainStatus getSingleton() {
        if (null == instance) {
            instance = new MainStatus();
            instance.isLogin = false;
            instance.isSuperuser = false;
            instance.stuClassName = "";
            instance.db = new DBHelper();
        }
        return instance;
    }

    private MainStatus() {
    }

    public static void close() {
        if (null != instance) {
            instance.db.close();
        }
    }
}