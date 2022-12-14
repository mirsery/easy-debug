package com.mirsery.easy.page;

import com.mirsery.easy.bean.EasyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/11
 */
@Component
public class BodyJPanel extends JPanel {

    @Resource
    private EasyClient client;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TextArea area = new TextArea("");

    private final JButton jButton = new JButton("clear");

    private JLabel serverAddressLab = new JLabel("server address: ");

    private JTextField jTextField = new JTextField("127.0.0.1");

    private JLabel protocolLab = new JLabel("Protocol: ");

    private final JRadioButton wsProtocol = new JRadioButton("ws", true);

    private final JRadioButton wssProtocol = new JRadioButton("wss");

    private final ButtonGroup group = new ButtonGroup();

    private JButton connectBtn = new JButton("connect");

    private TextArea content = new TextArea("");

    private JButton sendBtn = new JButton("send");

    public BodyJPanel() {
        group.add(wsProtocol);
        group.add(wssProtocol);
        loadLayout();
        loadComponent();
        loadListener();
    }

    private void loadLayout() {

        SpringLayout springLayout = new SpringLayout();

        springLayout.putConstraint(SpringLayout.NORTH, area, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, area, 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, area, -5, SpringLayout.EAST, this);

        springLayout.putConstraint(SpringLayout.NORTH, jButton, 5, SpringLayout.SOUTH, area);
        springLayout.putConstraint(SpringLayout.EAST, jButton, -20, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.WEST, jButton, 20, SpringLayout.WEST, this);


        springLayout.putConstraint(SpringLayout.WEST, serverAddressLab, 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, serverAddressLab, 10, SpringLayout.SOUTH, jButton);
        jTextField.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.NORTH, jTextField, 10, SpringLayout.SOUTH, jButton);
        springLayout.putConstraint(SpringLayout.WEST, jTextField, 5, SpringLayout.EAST, serverAddressLab);
        springLayout.putConstraint(SpringLayout.NORTH, protocolLab, 10, SpringLayout.SOUTH, jButton);
        springLayout.putConstraint(SpringLayout.NORTH, wsProtocol, 10, SpringLayout.SOUTH, jButton);
        springLayout.putConstraint(SpringLayout.NORTH, wssProtocol, 10, SpringLayout.SOUTH, jButton);
        springLayout.putConstraint(SpringLayout.NORTH, connectBtn, 10, SpringLayout.SOUTH, jButton);

        springLayout.putConstraint(SpringLayout.WEST, protocolLab, 10, SpringLayout.EAST, jTextField);
        springLayout.putConstraint(SpringLayout.WEST, wsProtocol, 4, SpringLayout.EAST, protocolLab);
        springLayout.putConstraint(SpringLayout.WEST, wssProtocol, 4, SpringLayout.EAST, wsProtocol);
        springLayout.putConstraint(SpringLayout.WEST, connectBtn, 5, SpringLayout.EAST, wssProtocol);

        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, serverAddressLab, 0, SpringLayout.VERTICAL_CENTER, jTextField);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, protocolLab, 0, SpringLayout.VERTICAL_CENTER, jTextField);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, wsProtocol, 0, SpringLayout.VERTICAL_CENTER, protocolLab);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, wssProtocol, 0, SpringLayout.VERTICAL_CENTER, wsProtocol);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, connectBtn, 0, SpringLayout.VERTICAL_CENTER, wssProtocol);


        springLayout.putConstraint(SpringLayout.NORTH, content, 10, SpringLayout.SOUTH, serverAddressLab);
        springLayout.putConstraint(SpringLayout.WEST, content, 5, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.WEST, sendBtn, 20, SpringLayout.EAST, content);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, sendBtn, 0, SpringLayout.VERTICAL_CENTER, content);
        springLayout.putConstraint(SpringLayout.NORTH, sendBtn, 10, SpringLayout.SOUTH, serverAddressLab);

        this.setLayout(springLayout);
    }


    private void loadComponent() {

        this.add(area);
        this.add(jButton);

        this.add(serverAddressLab);
        this.add(jTextField);
        this.add(protocolLab);
        this.add(wsProtocol);
        this.add(wssProtocol);
        this.add(connectBtn);

        this.add(content);
        this.add(sendBtn);
    }


    private void loadListener() {

        jButton.addActionListener(e -> {
            this.area.setText("");
        });


        connectBtn.addActionListener(e -> {

            if ("connect".equals(this.connectBtn.getText())) {
                String url = jTextField.getText().trim();
                if (wsProtocol.isSelected()) {
                    url = "ws://" + url;
                } else {
                    url = "wss://" + url;
                }

                try {
                    client.setUrl(url);
                    client.connect();
                    this.connectBtn.setText("disconnect");
                } catch (InterruptedException | URISyntaxException ex) {

                    recordMessage("[Error] " + ex.getMessage());
                }
            } else {
                try {
                    client.close();
                } catch (InterruptedException ex) {
                    recordMessage("[Error] " + ex.getMessage());
                } finally {
                    this.connectBtn.setText("connect");
                }

            }

        });


        sendBtn.addActionListener(e -> client.send(content.getText().trim()));

    }

    public void recordMessage(String message) {
        this.area.append(message + "\n");
    }


}
