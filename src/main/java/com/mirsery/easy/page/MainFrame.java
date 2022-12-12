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
        this.setSize(900, 620);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(800, 200);
        this.setResizable(true);
        this.setTitle("websocket调试工具");
        this.setLayout(new GridLayout(2,1));
        this.setResizable(true);

        noticeJPanel.setSize(880, 400);

        noticeJPanel.setSize(880, 200);

        this.getContentPane().add(noticeJPanel);
        this.getContentPane().add(bodyJPanel);

        this.setVisible(true);
    }

}
