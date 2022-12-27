package com.mirsery.easy.page.panel;

import com.mirsery.easy.event.page.ModeEvent;
import com.mirsery.easy.event.page.ModeType;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;


/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Component
public class ServerModePanel extends JPanel {
    private JTextArea noticeArea;

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

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private MessageSource messageSource;

    public ServerModePanel() {

    }

    public void init() {
        this.noticeArea = new JTextArea("服务器模式显示区域");

        this.noticeArea.setEditable(false);

        this.modeSelect = new ModeComBox();
        this.modeSelect.serverInit(messageSource);

        this.clearBtn = new JButton("清屏");
        this.localLabel = new JLabel("本地端口号");
        this.port = new JTextField(" ");
        this.startBtn = new JButton("启动");

        this.clientLabel = new JLabel("客户端");


        this.clientSelect = new JComboBox<>();
        this.clientSelect.addItem("所有连接");

        this.disconnectBtn = new JButton("断开");

        this.content = new JTextArea();
        this.sendBtn = new JButton("发送");

        this.lodLayout();
        this.loadComponent();
        this.initListener();
    }

    public void reset(){
        modeSelect.removeAllItems();
        modeSelect.clientInit(messageSource);
    }

    private void initListener() {

        modeSelect.addActionListener(e -> {
            ModeEvent modeEvent = new ModeEvent(ModeType.serverMode);
            ModeItem modeItem = (ModeItem)modeSelect.getSelectedItem();
            if(modeItem == null) return;
            modeEvent.setTargetMode(modeItem.getValue());
            applicationEventPublisher.publishEvent(modeEvent);
        });

    }

    private void lodLayout() {
        SpringLayout springLayout = new SpringLayout();

        springLayout.putConstraint(SpringLayout.NORTH, noticeArea, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, noticeArea, 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, noticeArea, -5, SpringLayout.EAST, this);

        springLayout.putConstraint(SpringLayout.SOUTH, noticeArea, -10, SpringLayout.NORTH, modeSelect);

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
        this.add(noticeArea);
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
}
