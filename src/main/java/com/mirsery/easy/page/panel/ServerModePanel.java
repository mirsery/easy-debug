package com.mirsery.easy.page.panel;

import com.mirsery.easy.ProjectCommon;
import com.mirsery.easy.bean.server.EasyServer;
import com.mirsery.easy.event.page.ModeEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Component
public class ServerModePanel extends JPanel {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private ProjectCommon common;

    @Resource
    private EasyServer easyServer;

    private int serverBind;

    private JTextArea noticeArea;
    private JScrollPane noticePane;

    private ModeComBox modeSelect;

    private JButton clearBtn;

    private JLabel localLabel;

    private JTextField port;

    private JButton startBtn;

    private JLabel clientLabel;

    private JComboBox<String> clientSelect;

    private JButton disconnectBtn;

    private JTextArea content;

    private JButton sendBtn;

    public ServerModePanel() {

    }

    public void init() {

        this.noticeArea = new JTextArea("");
        this.noticeArea.setEditable(false);
        this.noticeArea.setFocusable(false);
        this.noticePane = new JScrollPane(this.noticeArea);
        this.noticePane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.noticePane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.noticePane.setFocusable(false);

        this.modeSelect = new ModeComBox();
        this.modeSelect.serverInit(common);

        this.clearBtn = new JButton(common.getValue(ProjectCommon.clear));
        this.localLabel = new JLabel(common.getValue(ProjectCommon.localPort));
        this.port = new JTextField(" ");
        this.startBtn = new JButton(common.getValue(ProjectCommon.start));

        this.clientLabel = new JLabel(common.getValue(ProjectCommon.client));

        this.clientSelect = new JComboBox<>();
        this.clientSelect.addItem(common.getValue(ProjectCommon.allConnects));

        this.disconnectBtn = new JButton(common.getValue(ProjectCommon.disconnect));

        this.content = new JTextArea();
        this.sendBtn = new JButton(common.getValue(ProjectCommon.send));

        this.lodLayout();
        this.loadComponent();
        this.initListener();
    }

    public void reset() {
        modeSelect.removeAllItems();
        modeSelect.serverInit(common);
    }

    private void initListener() {

        modeSelect.addActionListener(e -> {
            ModeEvent modeEvent = new ModeEvent(ProjectCommon.serverMode);
            ModeItem modeItem = (ModeItem) modeSelect.getSelectedItem();
            if (modeItem == null) return;
            modeEvent.setTargetMode(modeItem.getValue());
            applicationEventPublisher.publishEvent(modeEvent);
        });


        clearBtn.addActionListener(e -> clearNotice());

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverBind == 0) {
                    serverBind = 1;
                    String _port = port.getText().trim();
                    if ("".equals(_port)) {
                        recordMessage(common.getValue(ProjectCommon.error) + common.getValue(ProjectCommon.urlError));
                    }
                    try {
                        easyServer.setPort(Integer.parseInt(_port));
                        easyServer.connect();

                        startBtn.setText(common.getValue(ProjectCommon.disconnect));

                    } catch (InterruptedException ex) {

                        recordMessage(common.getValue(ProjectCommon.error) + ex.getMessage());
                    }
                } else {
                    serverBind = 0;
                    try {
                        easyServer.close();
                    } catch (InterruptedException ex) {
                        recordMessage(common.getValue(ProjectCommon.error) + ex.getMessage());
                    } finally {
                        startBtn.setText(common.getValue(ProjectCommon.connect));
                    }
                }
            }
        });


    }

    private void lodLayout() {
        SpringLayout springLayout = new SpringLayout();

        springLayout.putConstraint(SpringLayout.NORTH, noticePane, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, noticePane, 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, noticePane, -5, SpringLayout.EAST, this);

        springLayout.putConstraint(SpringLayout.SOUTH, noticePane, -10, SpringLayout.NORTH, modeSelect);

        springLayout.putConstraint(SpringLayout.WEST, modeSelect, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.WEST, clearBtn, 5, SpringLayout.EAST, modeSelect);

        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.NORTH, localLabel);
        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.NORTH, port);
        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.NORTH, startBtn);

        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.NORTH, localLabel);
        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.NORTH, port);
        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.NORTH, startBtn);


        springLayout.putConstraint(SpringLayout.WEST, localLabel, 10, SpringLayout.WEST,
                this);
        springLayout.putConstraint(SpringLayout.WEST, port, 5, SpringLayout.EAST,
                localLabel);
        springLayout.putConstraint(SpringLayout.WEST, startBtn, 10, SpringLayout.EAST,
                port);

        springLayout.putConstraint(SpringLayout.SOUTH, localLabel, -10, SpringLayout.NORTH, clientLabel);
        springLayout.putConstraint(SpringLayout.SOUTH, port, -10, SpringLayout.NORTH, clientLabel);
        springLayout.putConstraint(SpringLayout.SOUTH, startBtn, -10, SpringLayout.NORTH, clientLabel);

        springLayout.putConstraint(SpringLayout.SOUTH, localLabel, -10, SpringLayout.NORTH, clientSelect);
        springLayout.putConstraint(SpringLayout.SOUTH, port, -10, SpringLayout.NORTH, clientSelect);
        springLayout.putConstraint(SpringLayout.SOUTH, startBtn, -10, SpringLayout.NORTH, clientSelect);

        springLayout.putConstraint(SpringLayout.SOUTH, localLabel, -10, SpringLayout.NORTH, disconnectBtn);
        springLayout.putConstraint(SpringLayout.SOUTH, port, -10, SpringLayout.NORTH, disconnectBtn);
        springLayout.putConstraint(SpringLayout.SOUTH, startBtn, -10, SpringLayout.NORTH, disconnectBtn);


        springLayout.putConstraint(SpringLayout.WEST, clientLabel, 10, SpringLayout.WEST,
                this);
        springLayout.putConstraint(SpringLayout.WEST, clientSelect, 5, SpringLayout.EAST,
                clientLabel);
        springLayout.putConstraint(SpringLayout.WEST, disconnectBtn, 10, SpringLayout.EAST,
                clientSelect);


        /*
         * 水平对齐
         * **/
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, modeSelect, 0,
                SpringLayout.VERTICAL_CENTER, clearBtn);

        /*
         * 水平对齐
         * **/
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, localLabel, 0,
                SpringLayout.VERTICAL_CENTER, port);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, startBtn, 0,
                SpringLayout.VERTICAL_CENTER, port);

        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, clientLabel, 0,
                SpringLayout.VERTICAL_CENTER, clientSelect);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, disconnectBtn, 0,
                SpringLayout.VERTICAL_CENTER, clientSelect);


        springLayout.putConstraint(SpringLayout.SOUTH, clientLabel, -10, SpringLayout.NORTH,
                content);
        springLayout.putConstraint(SpringLayout.SOUTH, clientSelect, -10, SpringLayout.NORTH,
                content);
        springLayout.putConstraint(SpringLayout.SOUTH, disconnectBtn, -10, SpringLayout.NORTH,
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
        this.add(noticePane);

        this.add(modeSelect);
        this.add(clearBtn);

        this.add(localLabel);
        this.add(port);
        this.add(startBtn);

        this.add(clientLabel);
        this.add(clientSelect);
        this.add(disconnectBtn);

        this.add(content);
        this.add(sendBtn);

    }

    public void recordMessage(String message) {

        this.noticeArea.append(message + "\n");
        JScrollBar vertical = this.noticePane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    public void clearNotice() {
        this.noticeArea.setText("");
    }
}
