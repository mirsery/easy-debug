package com.mirsery.easy.page.easy.custom;

import javax.swing.*;
import java.awt.*;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/30
 *
 * @desc 一个带有滑动按钮的显示框
 */
public class AreaWithScroll {

    private final JTextArea jTextArea;

    private final JScrollPane jScrollPane;


    public AreaWithScroll() {
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }


    public Component getComponent() {
        return jScrollPane;
    }


    public void cleanArea() {
        jTextArea.setText("");
    }

    public void recordMessage(String message) {
        jTextArea.append(message + "\n");
        JScrollBar vertical = jScrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }
}
