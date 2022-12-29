package com.mirsery.easy.page;


import com.mirsery.easy.ProjectCommon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;


@Component
public class MainFrame extends JFrame {

    @Resource
    private MainPanel mainPanel;

    @Value("${project.version}")
    private String version;

    @Resource
    private ProjectCommon common;

    public void init() {

        MainFrame mainFrame = this;
        mainPanel.init();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                mainFrame.requestFocusInWindow(false);
            }
        });

        this.setMinimumSize(new Dimension(700, 480));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(800, 200);
        this.setTitle(common.getValue(ProjectCommon.title) + " " + version);
        this.setResizable(true);
        this.getContentPane().add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}