package ui;

import manager.LoginMgr;
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
    private static JFrame frame;

    private void login(String username, String password) {

        boolean isLogin = LoginMgr.getSingleton().login(username, password);

        if (isLogin && null != frame) {
            JOptionPane.showMessageDialog(frame, "登录成功", "提示", JOptionPane.DEFAULT_OPTION);
            frame.dispose();
        }
    }

    private int checkUserProfile(String username, String password) {
        if ("".equals(username) || username.length() <= 0) {
            JOptionPane.showMessageDialog(frame, "请输入用户名", "提示", JOptionPane.WARNING_MESSAGE);
            return -1;
        } else if (null == passwordTextField.getPassword()) {
            JOptionPane.showMessageDialog(frame, "请输入密码", "提示", JOptionPane.WARNING_MESSAGE);
            return -2;
        }
        return 1;
    }

    private void bindEvent() {
        usernameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    passwordTextField.requestFocus();
                }
                super.keyPressed(e);
            }
        });

        passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    loginButton.doClick();
                    frame.dispose();
                }
                super.keyPressed(e);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText().trim();
                String password = new String(passwordTextField.getPassword()).trim();

                int isValid = checkUserProfile(username, password);
                if (0 < isValid) {
                    login(username, password);
                }

            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameTextField.setText("");
                passwordTextField.setText("");
                usernameTextField.requestFocus();
            }
        });

    }

    public LoginWin() {
        frame = new JFrame("Login");
        frame.setContentPane(LoginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 居中
        WinHelper.makeFrameToCenter(frame);
        // 绑定事件
        bindEvent();

        frame.setVisible(true);
    }

}
