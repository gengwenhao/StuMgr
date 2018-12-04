package manager;

public class LoginMgr extends BaseMgr {

    public void loginByDBConnection(String username, String pwd) {
        if ("gengwenhao".equals(username) && "13945657337xX".equals(pwd)) {
            super.login();
        }
    }
}
