package ui;

import util.WinHelper;

import javax.swing.*;

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
    private JTextField birthday;
    private JTextField address;
    private JTextField phone;
    private JTextField email;
    private JTextField studentClass;
    private JTable table1;
    private static JFrame frame;

    public StuHomeWin() {
        frame = new JFrame("StuHomeWin");
        frame.setContentPane(stuHomePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 居中
        WinHelper.makeFrameToCenter(frame);
        // 绑定事件
        //bindEvent();

        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
