package ui;

import manager.LoginMgr;
import manager.MainStatus;
import util.WinHelper;

import javax.swing.*;
import java.awt.event.*;

public class LoginWin {
    private JLabel titleLabel;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JTextField usernameTextField;
    private JPanel LoginPanel;
    private JButton resetBtn;
    private static JFrame mainFrame;

    // 验证用户和密码的正确性
    private void login(String username, String password) {
        // 使用LoginMgr验证登录
        boolean isLogin = LoginMgr.getSingleton().login(username, password);

        if (isLogin && null != mainFrame) {
            // 登录成功
            JOptionPane.showMessageDialog(mainFrame, "登录成功", "提示", JOptionPane.DEFAULT_OPTION);

            // 判断登录类型是学生还是超级管理员
            boolean isStudent = MainStatus.getSingleton().isStudent();
            if (isStudent) {
                new StuHomeWin();
            } else {
                // show superuserHomeWin
            }

            // 释放登录窗口
            mainFrame.dispose();
        } else {
            // 登录失败
            JOptionPane.showMessageDialog(mainFrame, "登录失败", "提示", JOptionPane.DEFAULT_OPTION);
        }
    }

    // 验证用户和密码的输入合法性
    private int checkUserProfile(String username, String password) {
        if ("".equals(username) || username.length() <= 0) {
            JOptionPane.showMessageDialog(mainFrame, "请输入用户名", "提示", JOptionPane.WARNING_MESSAGE);
            return -1;
        } else if ("".equals(password) || password.length() <= 0) {
            JOptionPane.showMessageDialog(mainFrame, "请输入密码", "提示", JOptionPane.WARNING_MESSAGE);
            return -2;
        }
        return 1;
    }

    // 清除用户名密码的已输入内容，将焦点放到用户名输入框
    private void clearInput() {
        usernameTextField.setText("");
        passwordTextField.setText("");
        usernameTextField.requestFocus();
    }

    // 事件绑定
    private void bindEvent() {

        // 用户名输入框按回车事件
        usernameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    passwordTextField.requestFocus();
                }
                super.keyPressed(e);
            }
        });

        // 密码输入框按回车事件
        passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    loginButton.doClick();
                    mainFrame.dispose();
                }
                super.keyPressed(e);
            }
        });

        // 登录按钮点击事件
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText().trim();
                String password = new String(passwordTextField.getPassword()).trim();

                // 检查用户名和密码输入框的长度
                int isValid = checkUserProfile(username, password);
                if (0 < isValid) {
                    login(username, password);
                }

            }
        });

        // 重置按钮点击事件
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 清空输入框
                clearInput();
            }
        });

    }

    public LoginWin() {
        mainFrame = new JFrame("登录");
        mainFrame.setContentPane(LoginPanel);
        mainFrame.setSize(450, 220);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WinHelper.exitCloseDBConnection(mainFrame);
            }
        });

        // 居中
        WinHelper.makeFrameToCenter(mainFrame);

        // 绑定事件
        bindEvent();

        mainFrame.setVisible(true);
    }

}
