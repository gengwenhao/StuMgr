package util;

import db.CourseProfile;
import db.ScoreProfile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class JTableHelper {
    public static void addCourseProfileToJTable(JTable table, String[] nameList, CourseProfile[] courseList) {
        Vector vData = new Vector();
        Vector vName = new Vector();

        for (String colName : nameList) {
            vName.add(colName);
        }

        for (CourseProfile profile : courseList) {
            Vector vRow = new Vector();
            vRow.add(profile.name);
            vRow.add(profile.periodExpriment);
            vRow.add(profile.credit);
            vRow.add(profile.type);
            vData.add(vRow.clone());
        }

        DefaultTableModel model = new DefaultTableModel(vData, vName);
        table.setModel(model);
    }

    public static void addScoreProfileToJTable(JTable table, String[] nameList, ScoreProfile[] scoreList) {
        Vector vData = new Vector();
        Vector vName = new Vector();

        for (String colName : nameList) {
            vName.add(colName);
        }

        for (ScoreProfile profile : scoreList) {
            Vector vRow = new Vector();
            vRow.add(profile.courseName);
            vRow.add(profile.grade);
            vData.add(vRow.clone());
        }

        DefaultTableModel model = new DefaultTableModel(vData, vName);
        table.setModel(model);
    }

    public static void addScoreDetailToJTable(JTable table, String[] nameList, ScoreProfile[] scoreList) {
        Vector vData = new Vector();
        Vector vName = new Vector();

        for (String colName : nameList) {
            vName.add(colName);
        }

        for (ScoreProfile profile : scoreList) {
            Vector vRow = new Vector();
            vRow.add(profile.stuName);
            vRow.add(profile.grade);
            vRow.add(profile.stuNumber);
            vData.add(vRow.clone());
        }

        DefaultTableModel model = new DefaultTableModel(vData, vName);
        table.setModel(model);
    }
}
