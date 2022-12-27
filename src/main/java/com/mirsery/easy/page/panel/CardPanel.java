package com.mirsery.easy.page.panel;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;

/**
 * easy-websocket
 *
 * @author ls
 * @date 2022/12/27
 */
@Component
public class CardPanel extends JPanel {

    @Resource
    private ClientModePanel clientModePanel;

    @Resource
    private ServerModePanel serverModePanel;

    private CardLayout cardLayout;


    public void init() {
        clientModePanel.init();
        serverModePanel.init();

        loadLayoutAndComponent();

    }


    private void loadLayoutAndComponent() {
        cardLayout = new CardLayout(10, 10);
        this.setLayout(cardLayout);

        this.add("serverMode", serverModePanel);
        this.add("clientMode", clientModePanel);


//        cardLayout.show(this,"serverMode");

        JPanel panel = this;

        new Thread(() -> {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cardLayout.show(panel, "clientMode");
        }).start();

    }

    public void change() {
        cardLayout.show(this, "serverMode");
    }
}
