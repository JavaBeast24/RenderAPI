package com.javabeast.rendering.api;

import javax.swing.*;
import java.awt.*;

public class Screen {

    public static void showFrameScreen(JFrame frame, int screen) {
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        if (screen > -1 && screen < gs.length) {
            gs[screen].setFullScreenWindow(frame);
        } else if (gs.length > 0) {
            gs[0].setFullScreenWindow(frame);
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }

}
