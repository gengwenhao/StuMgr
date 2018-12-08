package ui;

import db.CourseProfile;
import db.StuProfile;
import manager.CourseMgr;
import manager.StuMgr;
import util.WinHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.util.Vector;

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
    private static JFrame frame;
    private StuProfile profile;
    private CourseProfile[] courseList;
    Object[][] courseTableData;

    private void bindEvent() {
    }

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

        if (null != courseList && null != courseTableData) {
            for (int i = 0; i < courseList.length; i++) {
                Vector row = new Vector();
                row.add(courseList[i].name);
                row.add(courseList[i].periodExpriment);
                row.add(courseList[i].credit);
                row.add(courseList[i].type);
                ((DefaultTableModel) table1.getModel()).addColumn(row);
            }
        }
    }

    private void init() {
        profile = StuMgr.getSingleton().getStudentProfile();
        courseList = CourseMgr.getSingleton().getCourseList();

        updateStuProfile();
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Object[] columns = {"名称", "学时", "学分", "类型"};//字段
        courseTableData = new Object[500][4];
        DefaultTableModel model = new DefaultTableModel(courseTableData, columns);
        table1 = new JTable(model);
    }
}
