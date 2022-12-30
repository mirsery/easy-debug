package com.mirsery.easy.page.panel;

import com.mirsery.easy.ProjectCommon;
import com.mirsery.easy.bean.server.EasyServer;
import com.mirsery.easy.event.page.ModeEvent;
import com.mirsery.easy.page.easy.EasySpringLayout;
import com.mirsery.easy.page.easy.custom.AreaWithScroll;
import com.mirsery.easy.page.easy.custom.box.ClientItem;
import com.mirsery.easy.page.easy.custom.box.ModeComBox;
import com.mirsery.easy.page.easy.custom.box.ModeItem;
import com.mirsery.easy.page.easy.EasyPanelAdaptor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;


/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Component
public class ServerModePanel extends EasyPanelAdaptor {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private ProjectCommon common;

    @Resource
    private EasyServer easyServer;

    private int serverBind = 0;

    private AreaWithScroll noticeAreaScroll;

    private ModeComBox modeSelect;

    private JButton clearBtn;

    private JLabel localLabel;

    private JTextField port;

    private JButton startBtn;

    private JLabel clientLabel;

    private JComboBox<ClientItem> clientSelect;

    private JButton disconnectBtn;

    private JTextArea content;

    private JButton sendBtn;

    protected List<java.awt.Component> initComponent() {
        this.noticeAreaScroll = new AreaWithScroll();

        this.modeSelect = new ModeComBox();
        this.modeSelect.serverInit(common);

        this.clearBtn = new JButton(common.getValue(ProjectCommon.clear));
        this.localLabel = new JLabel(common.getValue(ProjectCommon.localPort));
        this.port = new JTextField();
        this.startBtn = new JButton(common.getValue(ProjectCommon.start));

        this.clientLabel = new JLabel(common.getValue(ProjectCommon.client));

        this.clientSelect = new JComboBox<>();
        ClientItem clientItem = new ClientItem(common.getValue(ProjectCommon.allConnects));
        clientItem.setValue(1);
        this.clientSelect.addItem(clientItem);

        this.disconnectBtn = new JButton(common.getValue(ProjectCommon.disconnect));

        this.content = new JTextArea();
        this.sendBtn = new JButton(common.getValue(ProjectCommon.send));

        return Arrays.asList(
                noticeAreaScroll.getComponent(), modeSelect, clearBtn, localLabel, port, startBtn, clientLabel,
                clientSelect, disconnectBtn, content, sendBtn
        );
    }


    protected void initListener() {

        modeSelect.addActionListener(e -> {
            ModeEvent modeEvent = new ModeEvent(ProjectCommon.serverMode);
            ModeItem modeItem = (ModeItem) modeSelect.getSelectedItem();
            if (modeItem == null) {
                return;
            }
            modeEvent.setTargetMode(modeItem.getValue());
            applicationEventPublisher.publishEvent(modeEvent);
        });

        clearBtn.addActionListener(e -> clearNotice());

        startBtn.addActionListener(e -> {
            if (serverBind == 0) {
                serverBind = 1;
                String _port = port.getText().trim();
                if ("".equals(_port)) {
                    recordMessage(common.getValue(ProjectCommon.error) + common.getValue(
                            ProjectCommon.urlError));
                    serverBind = 0;
                    return;
                }
                try {
                    easyServer.setPort(Integer.parseInt(_port));
                    easyServer.connect();

                    startBtn.setText(common.getValue(ProjectCommon.stop));

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
                    startBtn.setText(common.getValue(ProjectCommon.start));
                }
            }
        });

        disconnectBtn.addActionListener(e -> {
            ClientItem item = (ClientItem) clientSelect.getSelectedItem();
            if (item == null) {
                return;
            }
            if (item.getValue() != 0) {
                try {
                    easyServer.close();
                } catch (InterruptedException ex) {
                    recordMessage(common.getValue(ProjectCommon.error) + ex.getMessage());
                }
            } else {
                easyServer.disconnect(item.toString());
            }
        });

        sendBtn.addActionListener(e -> {

            String msg = content.getText().trim();
            if ("".equals(msg)) {
                return;
            }

            ClientItem item = (ClientItem) clientSelect.getSelectedItem();
            if (item == null) {
                return;
            }
            if (item.getValue() != 0) {
                easyServer.sendALl(msg);
            } else {
                easyServer.send(item.toString(), msg);
            }
        });


    }

    protected LayoutManager lodLayout() {
        EasySpringLayout easySpringLayout = new EasySpringLayout();
        SpringLayout springLayout = easySpringLayout.getSpringLayout();
        springLayout.putConstraint(SpringLayout.HEIGHT, content, 20, SpringLayout.HEIGHT, sendBtn);
        /* 设置水平对齐，同一row */
        easySpringLayout.rowVerticalCenter(modeSelect, clearBtn);
        easySpringLayout.rowVerticalCenter(localLabel, port, startBtn);
        easySpringLayout.rowVerticalCenter(clientLabel, clientSelect, disconnectBtn);
        easySpringLayout.rowVerticalCenter(sendBtn, content);
        /* 组件水平载入顺序以及间隔设置，自动伸缩组件定义 */
        easySpringLayout.addAutoRowComponent(this, 0, noticeAreaScroll.getComponent());
        easySpringLayout.addRowComponent(this, 10, modeSelect, clearBtn);
        easySpringLayout.addRowComponent(this, 5, localLabel, port, startBtn);
        easySpringLayout.addRowComponent(this, 5, clientLabel, clientSelect, disconnectBtn);
        easySpringLayout.addAutoRowComponent(this, 0, content, sendBtn);
        /* row间距设置以及自动伸缩row设置 */
        springLayout.putConstraint(SpringLayout.NORTH, noticeAreaScroll.getComponent(), 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, noticeAreaScroll.getComponent(), -10, SpringLayout.NORTH, modeSelect);
        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.NORTH, startBtn);
        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.NORTH, startBtn);
        springLayout.putConstraint(SpringLayout.SOUTH, startBtn, -10, SpringLayout.NORTH, disconnectBtn);
        springLayout.putConstraint(SpringLayout.SOUTH, disconnectBtn, -10, SpringLayout.NORTH, content);
        springLayout.putConstraint(SpringLayout.SOUTH, content, -10, SpringLayout.SOUTH, this);
        return springLayout;
    }

    public void reset() {
        modeSelect.removeAllItems();
        modeSelect.serverInit(common);
    }

    public void recordMessage(String message) {
        noticeAreaScroll.recordMessage(message);
    }

    public void clearNotice() {
        noticeAreaScroll.cleanArea();
    }

    public void addItem(String remoteAddr) {
        this.clientSelect.addItem(new ClientItem(remoteAddr));
    }

    public void removeItem(String remoteAddr) {
        int num = this.clientSelect.getItemCount();
        for (int i = 0; i < num; i++) {
            if (clientSelect.getItemAt(i).toString().equals(remoteAddr)) {
                this.clientSelect.removeItemAt(i);
                return;
            }
        }
    }
}
