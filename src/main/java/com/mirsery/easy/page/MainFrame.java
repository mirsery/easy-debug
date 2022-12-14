package com.mirsery.easy.page;

import java.awt.Dimension;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;


@Component
public class MainFrame extends JFrame {

    @Resource
    private BodyJPanel bodyJPanel;

    public void init() {
        this.setMinimumSize(new Dimension(700, 480));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(800, 200);

        this.setTitle("websocket调试工具");

        this.setResizable(true);

        this.getContentPane().add(bodyJPanel);

        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

}
