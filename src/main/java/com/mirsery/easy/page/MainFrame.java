package com.mirsery.easy.page;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;


@Component
public class MainFrame extends JFrame {
    @Resource
    private BodyJPanel bodyJPanel;

    public void init() {
        this.setSize(620, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(800, 200);

        this.setTitle("websocket调试工具");

        this.setResizable(true);

        this.getContentPane().add(bodyJPanel);

        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

}
