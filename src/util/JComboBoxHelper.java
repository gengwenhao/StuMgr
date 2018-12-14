package util;

import db.ClassProfile;

import javax.swing.*;

public class JComboBoxHelper {
    public static void addItemToJComboBox(JComboBox comboBox, String[] itemList) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (String item : itemList) {
            comboBox.addItem(item);
        }
    }

    public static void addClassProfilesToJComboBox(JComboBox comboBox, ClassProfile[] profiles) {
        if (null == profiles) {
            return;
        }

        for (ClassProfile profile : profiles) {
            comboBox.addItem(profile);
        }
    }

    public static int loadJComboBoxtoClassID(JComboBox comboBox, ClassProfile[] profiles) {
        return Integer.parseInt(profiles[comboBox.getSelectedIndex()].id);
    }
}
