package ui;

import manager.StuMgr;
import util.WinHelper;

import javax.swing.*;
import java.sql.ResultSet;

public class StuHomeWin {
    private JTabbedPane tabbedPane1;
    private JTable table2;
    private JLabel nameLabel;
    private JButton exitButton;
    private JPanel stuHomePanel;
    private JButton chooseOkButton;
    private JButton modifyButton;
    private JTextField studentCode;
    private JTextField name;
    private JTextField age;
    private JTextField sex;
    private JTextField address;
    private JTextField phone;
    private JTextField email;
    private JTextField studentClass;
    private JTable table1;
    private static JFrame frame;

    private void bindEvent() {
    }

    private void init() {
        ResultSet profile = StuMgr.getSingleton().getStudentProfile();

    }

    public StuHomeWin() {
        frame = new JFrame("StuHomeWin");
        frame.setContentPane(stuHomePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);

        init();

        // 居中
        WinHelper.makeFrameToCenter(frame);
        // 绑定事件
        bindEvent();

        frame.setVisible(true);
    }
}
