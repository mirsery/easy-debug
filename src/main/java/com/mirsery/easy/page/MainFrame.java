package com.mirsery.easy.page;


import com.mirsery.easy.ProjectCommon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;


@Component
public class MainFrame extends JFrame {

    @Resource
    private CardPanel cardPanel;

    @Value("${project.version}")
    private String version;

    @Resource
    private ProjectCommon common;

    public void init() {

        cardPanel.init();

        this.setMinimumSize(new Dimension(700, 480));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(800, 200);

        this.setTitle(common.getValue(ProjectCommon.title) + " " + version);

        this.setResizable(true);

        this.getContentPane().add(cardPanel);

        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }


}
