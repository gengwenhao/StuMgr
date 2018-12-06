package util;

import javax.swing.*;
import java.awt.*;

public class WinHelper {

    public static void makeFrameToCenter(JFrame frame) {
        //获得窗口宽
        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();

        //定义工具包
        Toolkit kit = Toolkit.getDefaultToolkit();
        //获取屏幕的尺寸
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        //设置窗口居中显示
        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }

}
