package util;

import db.ClassProfile;
import db.CourseProfile;
import db.StuProfile;

import javax.swing.*;

public class JComboBoxHelper {
    public static void addItemToJComboBox(JComboBox comboBox, String[] itemList) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (String item : itemList) {
            comboBox.addItem(item);
        }
    }

    // 添加班级信息到ComboBox中
    public static void addClassProfilesToJComboBox(JComboBox comboBox, ClassProfile[] profiles) {
        if (null == profiles) {
            return;
        }

        for (ClassProfile profile : profiles) {
            comboBox.addItem(profile);
        }
    }

    // 添加课程信息到ComboBox中
    public static void addCourseProfilesToJComboBox(JComboBox comboBox, CourseProfile[] profiles) {
        if (null == profiles) {
            return;
        }

        for (CourseProfile profile : profiles) {
            comboBox.addItem(profile);
        }
    }

    // 添加学生信息到ComboBox中
    public static void addStuProfilesToJComboBox(JComboBox comboBox, StuProfile[] profiles) {
        if (null == profiles) {
            return;
        }

        for (StuProfile profile : profiles) {
            comboBox.addItem(profile);
        }
    }

    // 根据ComboBox控件的选择获取课程ID
    public static int loadJComboBoxToClassID(JComboBox comboBox, ClassProfile[] profiles) {
        return Integer.parseInt(profiles[comboBox.getSelectedIndex()].id);
    }

    // 根据ComboBox控件的选择获取学生ID
    public static int loadJComboBoxToStuID(JComboBox comboBox, StuProfile[] profiles) {
        return Integer.parseInt(profiles[comboBox.getSelectedIndex()].sid);
    }

    // 根据ComboBox控件的选择获取课程ID
    public static int loadJComboBoxToCourseID(JComboBox comboBox, CourseProfile[] profiles) {
        return Integer.parseInt(profiles[comboBox.getSelectedIndex()].id);
    }

}
