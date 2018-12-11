package manager;

import sun.applet.Main;

public class LoginMgr {
    private static LoginMgr instance;
    private static MainStatus status;

    public static LoginMgr getSingleton() {
        if (null == status) {
            status = MainStatus.getSingleton();
        }
        if (null == instance) {
            instance = new LoginMgr();
        }
        return instance;
    }

    private LoginMgr() {

    }

    public boolean login(String username, String password) {
        if (null == status) {
            status = MainStatus.getSingleton();
        }

        int studentID = status.db.stuLogin(username, password);
        int superUserID = status.db.superUserLogin(username, password);

        if (-1 != studentID || -1 != superUserID) {
            status.userID = studentID > superUserID ? studentID : superUserID;
            status.isSuperuser = superUserID > studentID;
            status.isLogin = true;
            return true;
        }

        return false;
    }

    // 登出
    public void logout() {
        MainStatus.logout();
    }
}
