package ui;

import db.*;
import manager.ClassMgr;
import manager.CourseMgr;
import manager.ScoreMgr;
import manager.StuMgr;
import util.JComboBoxHelper;
import util.JTableHelper;
import util.WinHelper;

import javax.swing.*;
import java.awt.event.*;

public class SuperuserHomeWin {
    final static int WIDTH = 850;
    final static int HEIGHT = 450;
    final static boolean RESIZE_ABLE = false;
    final static String TITLE = "管理员界面";
    final static String[] NEW_STUDENT_DETAIL_TITLE = new String[]{
            "*学号", "*姓名", "*密码", "性别", "年龄", "地址", "手机号", "邮箱"
    };
    final static String[] NEW_COURSE_DETAIL_TITLE = new String[]{
            "*课程名称", "*学时", "*学分", "*课程类型"
    };

    private JButton exitButton;
    private JLabel nameLabel;
    private JTabbedPane mainPanel;
    private JPanel superuserHomePanel;
    private JButton saveCourseButton;
    private JButton saveStudentTableButton;
    private JButton cleanButton;
    private JTable courseListTable;
    private JTable stuScoreDetailTable;
    private JTable newCourseTable;
    private JTable studentDetailProfileTable;
    private JTable table4;
    private JTable courseScoreTable;
    private JScrollPane courseScorePanel;
    private JComboBox classComboBox;
    private JButton refreshBtn;
    private static JFrame mainFrame;

    private SuperuserProfile superuserProfile;
    private CourseProfile[] courseProfiles;
    private ScoreProfile[] scoreProfile;
    private ClassProfile[] classProfiles;

    // 更新 《该课程成绩》 表格
    private void updateCourseScoreTable(String courseID) {
        scoreProfile = ScoreMgr.getSingleton().getCourseScoreList(Integer.parseInt(courseID));
        JTableHelper.addScoreDetailToJTable(
                courseScoreTable,
                new String[]{"姓名", "分数", "学号"},
                scoreProfile);

    }

    // 绑定事件
    private void bindEvent() {
        // 刷新按钮点击事件
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                init();
            }
        });

        // 退出登录按钮点击事件
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginWin();
                WinHelper.exitCloseDBConnection(mainFrame, false);
            }
        });

        // 双击课程表事件
        courseListTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // 显示对应学生分数
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
                    updateCourseScoreTable(courseProfiles[row].id);
                }
            }
        });

        // 保存新课程
        saveCourseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[][] data = JTableHelper.loadJTableToProfile(newCourseTable, 1);

                boolean isNone = true;
                for (String field :
                        data[0]) {
                    isNone = null == field;
                }

                if (isNone) {
                    return;
                }

                // 添加新课程
                boolean createdSuccess = CourseMgr.getSingleton().addCourse(
                        data[0][0].trim(),
                        Integer.parseInt(data[0][1].trim()),
                        Integer.parseInt(data[0][2].trim()),
                        data[0][3].trim()
                );

                if (createdSuccess) {
                    JOptionPane.showMessageDialog(mainFrame, "保存成功", "提示", JOptionPane.DEFAULT_OPTION);
                    // 清空输入信息
                    JTableHelper.addTitleToJTable(newCourseTable, NEW_COURSE_DETAIL_TITLE);
                    init();
                }
            }
        });

        // 保存学生信息按钮点击事件
        saveStudentTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int classID = JComboBoxHelper.loadJComboBoxtoClassID(classComboBox, classProfiles);
                StuProfile stuProfile = JTableHelper.loadJTableToStuProfile(studentDetailProfileTable);

                boolean createdSuccess = StuMgr.getSingleton().addStudent(classID, stuProfile);
                if (createdSuccess) {
                    JOptionPane.showMessageDialog(mainFrame, "保存成功", "提示", JOptionPane.DEFAULT_OPTION);
                    // 清空输入信息
                    JTableHelper.addTitleToJTable(newCourseTable, NEW_COURSE_DETAIL_TITLE);
                    init();
                }
            }
        });
    }

    // 显示学生信息界面信息
    private void setWinProfile() {

        if (null == superuserProfile) {
            return;
        }

        // 设置管理员信息
        nameLabel.setText(superuserProfile.username);

        // 显示课程列表
        JTableHelper.addCourseProfileToJTable(courseListTable, new String[]{"名称", "学时", "学分", "类型"}, courseProfiles);

    }

    // 初始化界面
    private void init() {
        // 获取数据
        superuserProfile = StuMgr.getSingleton().getSuperuserProfile();
        courseProfiles = CourseMgr.getSingleton().getAllCourseList();
        classProfiles = ClassMgr.getSingleton().getAllClassList();

        // 给表格设置标题
        JTableHelper.addTitleToJTable(studentDetailProfileTable, NEW_STUDENT_DETAIL_TITLE);
        JTableHelper.addTitleToJTable(newCourseTable, NEW_COURSE_DETAIL_TITLE);
        JComboBoxHelper.addClassProfilesToJComboBox(classComboBox, classProfiles);

        setWinProfile();
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
