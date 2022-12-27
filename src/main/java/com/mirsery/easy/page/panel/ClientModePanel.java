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
 * @author ls
 * @date 2022/12/27
 */
@Component
public class ClientModePanel extends JPanel {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private MessageSource messageSource;

    private JTextArea noticeArea;

    private ModeComBox modeSelect;

    private JButton clearBtn;

    private JLabel addressLabel;

    private JTextField serverAddress;

    private JButton startBtn;

    private JTextArea content;

    private JButton sendBtn;

    public ClientModePanel() {

    }

    public void init() {
        this.noticeArea = new JTextArea();

        this.noticeArea = new JTextArea("客户端模式显示区域");

        this.noticeArea.setEditable(false);

        this.modeSelect = new ModeComBox();
        this.modeSelect.clientInit(messageSource);

        this.clearBtn = new JButton("清屏");

        this.addressLabel = new JLabel("远程服务器地址");
        this.serverAddress = new JTextField(" ");
        this.startBtn = new JButton("连接");

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
            ModeEvent modeEvent = new ModeEvent(ModeType.clientMode);
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

        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.NORTH, addressLabel);
        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.NORTH, serverAddress);
        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.NORTH, startBtn);

        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.NORTH, addressLabel);
        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.NORTH, serverAddress);
        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.NORTH, startBtn);


        springLayout.putConstraint(SpringLayout.WEST, addressLabel, 10, SpringLayout.WEST,
                this);
        springLayout.putConstraint(SpringLayout.WEST, serverAddress, 5, SpringLayout.EAST,
                addressLabel);
        springLayout.putConstraint(SpringLayout.EAST, serverAddress, -5, SpringLayout.WEST,
                startBtn);

        springLayout.putConstraint(SpringLayout.EAST, startBtn, -20, SpringLayout.EAST,
                this);


        /*
         * 水平对齐
         * **/
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, modeSelect, 0,
                SpringLayout.VERTICAL_CENTER, clearBtn);

        /*
         * 水平对齐
         * **/
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, addressLabel, 0,
                SpringLayout.VERTICAL_CENTER, serverAddress);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, startBtn, 0,
                SpringLayout.VERTICAL_CENTER, serverAddress);


        springLayout.putConstraint(SpringLayout.SOUTH, addressLabel, -10, SpringLayout.NORTH,
                content);
        springLayout.putConstraint(SpringLayout.SOUTH, serverAddress, -10, SpringLayout.NORTH,
                content);
        springLayout.putConstraint(SpringLayout.SOUTH, startBtn, -10, SpringLayout.NORTH,
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

        this.add(addressLabel);
        this.add(serverAddress);
        this.add(startBtn);

        this.add(content);
        this.add(sendBtn);

    }
}
