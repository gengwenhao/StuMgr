package manager;

public class BaseMgr implements Manageable {
    private static boolean isLogin;
    private static boolean isSuperuser;

    BaseMgr() {
        isLogin = false;
        isSuperuser = false;
    }

    @Override
    public void login() {
        isLogin = true;
    }

    @Override
    public void logout() {
        isLogin = false;
    }


    public void setSuperuser(boolean superuser) {
        isSuperuser = superuser;
    }

    public boolean isSuperuser() {
        return isSuperuser;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
