package com.mirsery.easy.page;

import com.mirsery.easy.bean.client.EasyClient;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;


/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/11
 */
public class BodyJPanel extends JPanel {

    private EasyClient client;

    private MessageSource messageSource;

    private JTextArea area;

    private JButton jButton;

    private JLabel serverAddressLab;

    private JTextField jTextField;

    private JLabel protocolLab;

    private JRadioButton wsProtocol;

    private JRadioButton wssProtocol;

    private ButtonGroup group;

    private JButton connectBtn;
    private int connectValue;

    private JTextArea content;

    private JButton sendBtn;

    public BodyJPanel(EasyClient client, MessageSource messageSource) {
        this.client = client;
        this.messageSource = messageSource;
        loadLayout();
        loadComponent();
        loadListener();
    }

    private void loadLayout() {

        area = new JTextArea("");

        jButton = new JButton(messageSource.getMessage("clear", null, LocaleContextHolder.getLocale()));

        serverAddressLab = new JLabel(messageSource.getMessage("serverAddressDesc", null, LocaleContextHolder.getLocale()));

        jTextField = new JTextField("127.0.0.1");

        protocolLab = new JLabel(messageSource.getMessage("protocol", null, LocaleContextHolder.getLocale()));

        wsProtocol = new JRadioButton("ws", true);

        wssProtocol = new JRadioButton("wss");

        group = new ButtonGroup();

        connectBtn = new JButton(messageSource.getMessage("connect", null, LocaleContextHolder.getLocale()));
        connectValue = 0;

        content = new JTextArea("");

        sendBtn = new JButton(messageSource.getMessage("send", null, LocaleContextHolder.getLocale()));

        group.add(wsProtocol);
        group.add(wssProtocol);

        SpringLayout springLayout = new SpringLayout();
        area.setEditable(false);
        area.setFocusable(false);

        springLayout.putConstraint(SpringLayout.NORTH, area, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, area, 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, area, -5, SpringLayout.EAST, this);

        springLayout.putConstraint(SpringLayout.SOUTH, area, -10, SpringLayout.NORTH, serverAddressLab);

        springLayout.putConstraint(SpringLayout.WEST, serverAddressLab, 10, SpringLayout.WEST, this);
        jTextField.setPreferredSize(new Dimension(300, 20));
        springLayout.putConstraint(SpringLayout.WEST, jTextField, 5, SpringLayout.EAST, serverAddressLab);
        springLayout.putConstraint(SpringLayout.EAST, jTextField, -20, SpringLayout.EAST, this);

        springLayout.putConstraint(SpringLayout.SOUTH, jTextField, -10, SpringLayout.NORTH, protocolLab);
        springLayout.putConstraint(SpringLayout.SOUTH, jTextField, -10, SpringLayout.NORTH, wsProtocol);
        springLayout.putConstraint(SpringLayout.SOUTH, jTextField, -10, SpringLayout.NORTH, wssProtocol);
        springLayout.putConstraint(SpringLayout.SOUTH, jTextField, -10, SpringLayout.NORTH, connectBtn);
        springLayout.putConstraint(SpringLayout.SOUTH, jTextField, -10, SpringLayout.NORTH, jButton);

        springLayout.putConstraint(SpringLayout.SOUTH, serverAddressLab, -10, SpringLayout.NORTH, protocolLab);
        springLayout.putConstraint(SpringLayout.SOUTH, serverAddressLab, -10, SpringLayout.NORTH, wsProtocol);
        springLayout.putConstraint(SpringLayout.SOUTH, serverAddressLab, -10, SpringLayout.NORTH, wssProtocol);
        springLayout.putConstraint(SpringLayout.SOUTH, serverAddressLab, -10, SpringLayout.NORTH, connectBtn);
        springLayout.putConstraint(SpringLayout.SOUTH, serverAddressLab, -10, SpringLayout.NORTH, jButton);


        springLayout.putConstraint(SpringLayout.WEST, protocolLab, 10, SpringLayout.WEST,
                this);
        springLayout.putConstraint(SpringLayout.WEST, wsProtocol, 4, SpringLayout.EAST,
                protocolLab);
        springLayout.putConstraint(SpringLayout.WEST, wssProtocol, 4, SpringLayout.EAST,
                wsProtocol);
        springLayout.putConstraint(SpringLayout.WEST, connectBtn, 5, SpringLayout.EAST,
                wssProtocol);
        springLayout.putConstraint(SpringLayout.WEST, jButton, 5, SpringLayout.EAST,
                connectBtn);

        /*
         * 水平对齐
         * **/
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, serverAddressLab, 0,
                SpringLayout.VERTICAL_CENTER, jTextField);


        springLayout.putConstraint(SpringLayout.SOUTH, protocolLab, -10, SpringLayout.NORTH,
                content);
        springLayout.putConstraint(SpringLayout.SOUTH, wsProtocol, -10, SpringLayout.NORTH,
                content);
        springLayout.putConstraint(SpringLayout.SOUTH, wssProtocol, -10, SpringLayout.NORTH,
                content);
        springLayout.putConstraint(SpringLayout.SOUTH, connectBtn, -10, SpringLayout.NORTH,
                content);
        springLayout.putConstraint(SpringLayout.SOUTH, jButton, -10, SpringLayout.NORTH,
                content);

        springLayout.putConstraint(SpringLayout.HEIGHT, content, 20, SpringLayout.HEIGHT, sendBtn);

        springLayout.putConstraint(SpringLayout.WEST, content, 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, content, -10, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.EAST, content, -20, SpringLayout.WEST, sendBtn);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, sendBtn, 0,
                SpringLayout.VERTICAL_CENTER, content);

        springLayout.putConstraint(SpringLayout.EAST, sendBtn, -10, SpringLayout.EAST, this);


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

            if (connectValue == 0) {
                connectValue = 1;
                String url = jTextField.getText().trim();
                if (wsProtocol.isSelected()) {
                    url = "ws://" + url;
                } else {
                    url = "wss://" + url;
                }

                try {
                    client.setUrl(url);
                    client.connect();
                    this.connectBtn.setText(messageSource.getMessage("disconnect", null, LocaleContextHolder.getLocale()));
                } catch (InterruptedException | URISyntaxException ex) {

                    recordMessage("[Error] " + ex.getMessage());
                }
            } else {
                connectValue = 0;
                try {
                    client.close();
                } catch (InterruptedException ex) {
                    recordMessage("[Error] " + ex.getMessage());
                } finally {
                    this.connectBtn.setText(messageSource.getMessage("connect", null, LocaleContextHolder.getLocale()));
                }
            }

        });

        sendBtn.addActionListener(e -> client.send(content.getText().trim()));

    }

    public void recordMessage(String message) {
        this.area.append(message + "\n");
    }


}
