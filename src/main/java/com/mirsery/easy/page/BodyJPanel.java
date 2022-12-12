package com.mirsery.easy.page;

import com.mirsery.easy.bean.IotClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.net.URI;
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
    private IotClient client;

    @Resource
    private NoticeJPanel noticeJPanel;

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

        this.add(serverAddressLab);
        this.add(jTextField);
        this.add(protocolLab);
        this.add(wsProtocol);
        this.add(wssProtocol);
        this.add(connectBtn);
        this.add(content);
        this.add(sendBtn);

        initListener();
    }


    private void initListener() {

        connectBtn.addActionListener(e -> {

            if ("connect".equals(this.connectBtn.getText())) {
                String url = jTextField.getText().trim();
                if (wsProtocol.isSelected()) {
                    url = "ws://" + url;
                } else {
                    url = "wss://" + url;
                }

                try {
                    client.setURI(new URI(url));
                    client.connect();
                    this.connectBtn.setText("disconnect");
                } catch (URISyntaxException ex) {
                    noticeJPanel.recordMessage("url格式不正确，请核实后输入");
                }
            } else {
                client.close();
                this.connectBtn.setText("connect");
            }


        });


        sendBtn.addActionListener(e -> client.send(content.getText().trim()));

    }


}
