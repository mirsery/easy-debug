package com.mirsery.easy.page;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class NoticeJPanel extends JPanel {

    private final TextArea area = new TextArea();

    private final JButton jButton = new JButton("clear");


    public NoticeJPanel() {
        this.add(area);
        this.area.setEditable(false);
        this.area.setEnabled(false);
        this.add(jButton);

        SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.NORTH, area, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, area, 10, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.EAST, area, -10, SpringLayout.WEST, jButton);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER,jButton,0,SpringLayout.VERTICAL_CENTER,area);
        layout.putConstraint(SpringLayout.EAST, jButton, -10, SpringLayout.EAST, this);

        this.setLayout(layout);

        this.init();

    }

    private void init() {
        jButton.addActionListener(e -> {
            this.clearMessage();
        });
    }

    public void recordMessage(String message) {
        this.area.append(message + "\n");
    }

    public void clearMessage() {
        this.area.setText("");
    }

}
