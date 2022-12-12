package com.mirsery.easy.page;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;


@Component
public class MainFrame extends JFrame {

    @Resource
    private NoticeJPanel noticeJPanel;

    @Resource
    private BodyJPanel bodyJPanel;

    public void init() {
        this.setSize(1800, 1200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(800, 200);
        this.setResizable(true);
        this.setTitle("websocket调试工具");

        this.setResizable(true);

        SpringLayout layout = new SpringLayout();

        layout.putConstraint(SpringLayout.NORTH, noticeJPanel, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, noticeJPanel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, noticeJPanel, -5, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.SOUTH, noticeJPanel, -5, SpringLayout.NORTH, bodyJPanel);
        layout.putConstraint(SpringLayout.SOUTH, bodyJPanel, -5, SpringLayout.SOUTH, this);

        this.getContentPane().add(noticeJPanel);
        this.getContentPane().add(bodyJPanel);

//        this.pack();
        this.setLocationRelativeTo(null);

//        this.setLayout(layout);
        this.setVisible(true);
    }

}
