package ui;

import db.CourseProfile;
import db.StuProfile;
import manager.CourseMgr;
import manager.LoginMgr;
import manager.MainStatus;
import manager.StuMgr;
import util.WinHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StuHomeWin {
    private JTabbedPane tabbedPane1;
    private JTable table2;
    private JLabel nameLabel;
    private JButton exitButton;
    private JPanel stuHomePanel;
    private JButton chooseOkButton;
    private JButton modifyButton;
    private JTextField studentCode;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField addressField;
    private JTextField mobileField;
    private JTextField emailField;
    private JTextField studentClassField;
    private JTable table1;
    // 窗口
    private static JFrame mainFrame;
    private StuProfile profile;
    private CourseProfile[] courseList;
    Object[][] courseTableData;

    // 绑定事件
    private void bindEvent() {

        // 退出登录
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginWin();
                WinHelper.exitCloseDBConnection(mainFrame);
            }
        });

    }

    // 刷新学生信息界面
    private void updateStuProfile() {

        if (null == profile) {
            return;
        }

        nameLabel.setText(profile.name);
        studentCode.setText(profile.sno);
        nameField.setText(profile.name);
        studentClassField.setText(profile.className);
        ageField.setText(profile.age + "");
        genderField.setText(profile.gender == 1 ? "男生" : "女生");
        addressField.setText(profile.address);
        mobileField.setText(profile.mobile);
        emailField.setText(profile.email);

    }

    // 初始化界面
    private void init() {
        profile = StuMgr.getSingleton().getStudentProfile();
        courseList = CourseMgr.getSingleton().getCourseList();

        updateStuProfile();
    }

    public StuHomeWin() {
        mainFrame = new JFrame("学生信息主页面");
        mainFrame.setContentPane(stuHomePanel);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setSize(400, 450);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WinHelper.exitCloseDBConnection(mainFrame);
            }
        });

        init();

        // 居中
        WinHelper.makeFrameToCenter(mainFrame);

        // 绑定事件
        bindEvent();

        mainFrame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Object[] columns = {"名称", "学时", "学分", "类型"};//字段
        courseTableData = new Object[500][4];
        DefaultTableModel model = new DefaultTableModel(courseTableData, columns);
        table1 = new JTable(model);
    }
}
