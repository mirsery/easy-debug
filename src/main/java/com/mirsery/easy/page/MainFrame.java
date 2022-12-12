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



        GridBagLayout bagLayout = new GridBagLayout();
        JPanel jPanel = new JPanel(bagLayout);

        GridBagConstraints c = null;

        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        bagLayout.addLayoutComponent(noticeJPanel,c);


        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        bagLayout.addLayoutComponent(bodyJPanel,c);

//        noticeJPanel.setSize(880, 400);
//        bodyJPanel.setSize(880, 200);

        jPanel.add(noticeJPanel);
        jPanel.add(bodyJPanel);

        this.getContentPane().add(jPanel);

        this.pack();
        this.setLocationRelativeTo(null);

        this.setLayout(bagLayout);
        this.setVisible(true);
    }

}
