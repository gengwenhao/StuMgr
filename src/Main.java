import ui.LoginWin;
import ui.StuHomeWin;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        new LoginWin();
        new StuHomeWin();
    }
}
