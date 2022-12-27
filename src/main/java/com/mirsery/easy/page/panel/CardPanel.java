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

        this.add("clientMode", clientModePanel);    //默认显示client模式
        this.add("serverMode", serverModePanel);
    }

    public void changeServerMode() {
        cardLayout.show(this, "serverMode");
    }

    public void changeClientMode() {
        cardLayout.show(this, "clientMode");
    }
    
    public void showMode(String mode) {
        cardLayout.show(this, mode);
    }
}
