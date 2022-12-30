package com.mirsery.easy.page.panel;

import com.mirsery.easy.ProjectCommon;
import com.mirsery.easy.bean.client.EasyClient;
import com.mirsery.easy.event.page.ModeEvent;
import com.mirsery.easy.page.easy.EasySpringLayout;
import com.mirsery.easy.page.easy.custom.AreaWithScroll;
import com.mirsery.easy.page.easy.custom.box.ModeComBox;
import com.mirsery.easy.page.easy.custom.box.ModeItem;
import com.mirsery.easy.page.easy.EasyPanelAdaptor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;


/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Component
public class ClientModePanel extends EasyPanelAdaptor {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private ProjectCommon common;

    @Resource
    private EasyClient client;

    private AreaWithScroll noticeAreaScroll;

    private ModeComBox modeSelect;

    private JButton clearBtn;

    private JLabel addressLabel;

    private JTextField serverAddress;

    private JButton startBtn;

    private JTextArea content;

    private JButton sendBtn;

    private int connectValue = 0;

    protected List<java.awt.Component> initComponent() {

        this.noticeAreaScroll = new AreaWithScroll();


        this.modeSelect = new ModeComBox();
        this.modeSelect.clientInit(common);

        this.clearBtn = new JButton(common.getValue(ProjectCommon.clear));

        this.addressLabel = new JLabel(common.getValue(ProjectCommon.serverAddressDesc));
        this.serverAddress = new JTextField();
        this.startBtn = new JButton(common.getValue(ProjectCommon.connect));

        this.content = new JTextArea();
        this.sendBtn = new JButton(common.getValue(ProjectCommon.send));

        return Arrays.asList(noticeAreaScroll.getComponent(), modeSelect, clearBtn, addressLabel, serverAddress,
                startBtn, content, sendBtn);
    }

    protected void initListener() {

        modeSelect.addActionListener(e -> {
            ModeEvent modeEvent = new ModeEvent(ProjectCommon.clientMode);
            ModeItem modeItem = (ModeItem) modeSelect.getSelectedItem();
            if (modeItem == null) {
                return;
            }
            modeEvent.setTargetMode(modeItem.getValue());
            applicationEventPublisher.publishEvent(modeEvent);
        });

        clearBtn.addActionListener(e -> clearNotice());

        startBtn.addActionListener(e -> {
            if (connectValue == 0) {
                connectValue = 1;
                String url = serverAddress.getText().trim();
                if ("".equals(url)) {
                    connectValue = 0;
                    recordMessage(common.getValue(ProjectCommon.error) + common.getValue(
                            ProjectCommon.urlError));
                    return;
                }
                try {
                    client.setUrl(url);
                    client.connect();
                    startBtn.setText(common.getValue(ProjectCommon.disconnect));
                } catch (InterruptedException | URISyntaxException ex) {
                    recordMessage(common.getValue(ProjectCommon.error) + ex.getMessage());
                }
            } else {
                connectValue = 0;
                try {
                    client.close();
                } catch (InterruptedException ex) {
                    recordMessage(common.getValue(ProjectCommon.error) + ex.getMessage());
                } finally {
                    startBtn.setText(common.getValue(ProjectCommon.connect));
                }
            }
        });

        sendBtn.addActionListener(e -> client.send(content.getText().trim()));

    }

    protected LayoutManager lodLayout() {
        EasySpringLayout easySpringLayout = new EasySpringLayout();
        SpringLayout springLayout = easySpringLayout.getSpringLayout();
        springLayout.putConstraint(SpringLayout.HEIGHT, content, 20, SpringLayout.HEIGHT, sendBtn);
        /* 设置水平对齐，同一row */
        easySpringLayout.rowVerticalCenter(modeSelect, clearBtn);
        easySpringLayout.rowVerticalCenter(addressLabel, serverAddress, startBtn);
        easySpringLayout.rowVerticalCenter(sendBtn, content);
        /* 组件水平载入顺序以及间隔设置，自动伸缩组件定义 */
        easySpringLayout.addAutoRowComponent(this, 5, 0, noticeAreaScroll.getComponent());
        easySpringLayout.addRowComponent(this, 10, modeSelect, clearBtn);
        easySpringLayout.addAutoRowComponent(this, 5, 1, addressLabel, serverAddress, startBtn);
        easySpringLayout.addAutoRowComponent(this, 5, 0, content, sendBtn);
        /* row间距设置以及自动伸缩row设置 */
        springLayout.putConstraint(SpringLayout.NORTH, noticeAreaScroll.getComponent(), 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, noticeAreaScroll.getComponent(), -10, SpringLayout.NORTH, modeSelect);
        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.NORTH, startBtn);
        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.NORTH, startBtn);
        springLayout.putConstraint(SpringLayout.SOUTH, startBtn, -10, SpringLayout.NORTH, content);
        springLayout.putConstraint(SpringLayout.SOUTH, content, -5, SpringLayout.SOUTH, this);

        return springLayout;

    }


    public void reset() {
        modeSelect.removeAllItems();
        modeSelect.clientInit(common);
    }

    public void restStartBtn() {
        connectValue = 0;
        startBtn.setText(common.getValue(ProjectCommon.connect));
    }


    public void recordMessage(String message) {
        noticeAreaScroll.recordMessage(message);
    }

    public void clearNotice() {
        noticeAreaScroll.cleanArea();
    }

}
