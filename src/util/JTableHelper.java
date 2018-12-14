package util;

import db.CourseProfile;
import db.ScoreProfile;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
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

    public static void addTitleToJTable(JTable table, String[] titleList) {
        Vector vData = new Vector();
        Vector vName = new Vector();

        for (String colName : titleList) {
            vName.add(colName);
        }

        vData.add(new Vector());

        DefaultTableModel model = new DefaultTableModel(vData, vName);
        table.setModel(model);
    }

    // 通过表格对象和类的字段信息返回实例列表
    public static String[][] loadJTableToProfile(JTable table, int row) {
        // 全部数据
        ArrayList<String[]> data = new ArrayList<String[]>();
        // 一行数据
        ArrayList<String> listR;


        // 判断是否row超出表格有效数据行数
        int rowCount = table.getRowCount(), colCount = table.getColumnCount();
        row = row < rowCount ? row : rowCount;

        // 取出数据
        for (int i = 0; i < row; i++) {
            listR = new ArrayList<String>();
            for (int j = 0; j < colCount; j++) {
                listR.add((String) table.getValueAt(i, j));
            }
            data.add(listR.toArray(new String[colCount]));
        }

        return data.toArray(new String[data.size()][colCount]);
    }
}
