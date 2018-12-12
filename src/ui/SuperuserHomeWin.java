package ui;

import db.CourseProfile;
import db.ScoreProfile;
import db.SuperuserProfile;
import manager.CourseMgr;
import manager.ScoreMgr;
import manager.StuMgr;
import util.JTableHelper;
import util.WinHelper;

import javax.swing.*;
import java.awt.event.*;

public class SuperuserHomeWin {
    final static int WIDTH = 850;
    final static int HEIGHT = 450;
    final static boolean RESIZE_ABLE = false;
    final static String TITLE = "管理员界面";

    private JButton exitButton;
    private JLabel nameLabel;
    private JTabbedPane tabbedPane1;
    private JPanel superuserHomePanel;
    private JButton addOneRowCourseButton;
    private JButton deleteOneRowCourseButton;
    private JButton addOneRowTeacherButton1;
    private JButton deleteOneRowTeacherButton1;
    private JButton saveCourseButton;
    private JButton saveTeacherButton;
    private JButton addOneStudentRowButton;
    private JButton saveStudentTableButton;
    private JButton deleteOneStudentRowButton;
    private JButton cleanButton;
    private JButton statisticButton;
    private JTabbedPane tabbedPane2;
    private JButton updateButton;
    private JTable courseListTable;
    private JTable stuScoreDetailTable;
    private JTable course;
    private JTable studentClass;
    private JTable student;
    private JTable time;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JTable table4;
    private static JFrame mainFrame;

    private SuperuserProfile superuserProfile;
    private CourseProfile[] courseProfiles;
    private ScoreProfile scoreProfile;

    private void updateScoreDetailByCID(String cid) {
        scoreProfile = ScoreMgr.getSingleton().
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

        // 双击课程表显示对应学生分数
        courseListTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //获得行位置
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
                    System.out.println("双击了\n" + courseProfiles[row] + "\n");
                }
            }
        });

    }

    // 刷新学生信息界面
    private void updateWinProfile() {

        if (null == superuserProfile) {
            return;
        }

        // 设置管理员信息
        nameLabel.setText(superuserProfile.username);

        // 添加所有课程信息
        JTableHelper.addCourseProfileToJTable(courseListTable, new String[]{"名称", "学时", "学分", "类型"}, courseProfiles);

    }

    // 初始化界面
    private void init() {
        superuserProfile = StuMgr.getSingleton().getSuperuserProfile();
        courseProfiles = CourseMgr.getSingleton().getAllCourseList();

        updateWinProfile();
    }

    public SuperuserHomeWin() {
        mainFrame = new JFrame(TITLE);
        mainFrame.setContentPane(superuserHomePanel);
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
