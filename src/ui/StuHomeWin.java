package ui;

import db.CourseProfile;
import db.ScoreProfile;
import db.StuProfile;
import manager.CourseMgr;
import manager.ScoreMgr;
import manager.StuMgr;
import util.JTableHelper;
import util.WinHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class StuHomeWin {
    final static int WIDTH = 850;
    final static int HEIGHT = 450;
    final static boolean RESIZE_ABLE = false;

    private JTabbedPane tabbedPane1;
    private JTable scoreTable;
    private JLabel nameLabel;
    private JButton exitButton;
    private JPanel stuHomePanel;
    private JButton modifyButton;
    private JTextField studentCode;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField addressField;
    private JTextField mobileField;
    private JTextField emailField;
    private JTextField studentClassField;
    private JTable courseTable;
    // 窗口
    private static JFrame mainFrame;
    private StuProfile profile;
    private CourseProfile[] courseList;
    private ScoreProfile[] scoreList;

    // 获取当前面板上的学生信息
    private StuProfile getStuProfile() {
        StuProfile profile = new StuProfile();
        profile.age = Integer.parseInt(ageField.getText());
        profile.address = addressField.getText();
        profile.email = emailField.getText();
        profile.mobile = mobileField.getText();
        return profile;
    }


    // 绑定事件
    private void bindEvent() {

        // 退出登录
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginWin();
                WinHelper.exitCloseDBConnection(mainFrame, false);
            }
        });

        // 修改学生信息
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = StuMgr.getSingleton()
                        .changeStudentProfile(getStuProfile());
                if (success) {
                    JOptionPane.showMessageDialog(mainFrame, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    init();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "修改失败", "提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }


    // 刷新学生信息界面
    private void updateStuProfile() {

        if (null == profile || null == courseTable) {
            return;
        }

        // 设置学生信息
        nameLabel.setText(profile.name);
        studentCode.setText(profile.sno);
        nameField.setText(profile.name);
        studentClassField.setText(profile.className);
        ageField.setText(profile.age + "");
        genderField.setText(profile.gender == 1 ? "男生" : "女生");
        addressField.setText(profile.address);
        mobileField.setText(profile.mobile);
        emailField.setText(profile.email);

        // 添加课程信息
        String[] courseArr = new String[]{"名称", "学时", "学分", "类型"};
        JTableHelper.addCourseProfileToJTable(courseTable, courseArr, courseList);

        // 添加课程信息
        String[] scoreArr = new String[]{"课程名称", "课程分数"};
        JTableHelper.addScoreProfileToJTable(scoreTable, scoreArr, scoreList);

    }

    // 初始化界面
    private void init() {
        profile = StuMgr.getSingleton().getStudentProfile();
        courseList = CourseMgr.getSingleton().getCourseList();
        scoreList = ScoreMgr.getSingleton().getScoreList();

        updateStuProfile();
    }

    public StuHomeWin() {
        mainFrame = new JFrame("学生信息主页面");
        mainFrame.setContentPane(stuHomePanel);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setResizable(RESIZE_ABLE);
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WinHelper.exitCloseDBConnection(mainFrame, false);
            }
        });

        init();

        // 居中
        WinHelper.makeFrameToCenter(mainFrame);

        // 绑定事件
        bindEvent();

        mainFrame.setVisible(true);
    }

}
