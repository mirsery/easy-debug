package com.mirsery.easy.page.panel;

import com.mirsery.easy.ProjectCommon;
import com.mirsery.easy.bean.client.EasyClient;
import com.mirsery.easy.event.page.ModeEvent;
import com.mirsery.easy.page.panel.box.ModeComBox;
import com.mirsery.easy.page.panel.box.ModeItem;
import com.mirsery.easy.page.panel.easy.EasyPanelAdaptor;
import java.awt.LayoutManager;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


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

    private JScrollPane noticePane;

    private JTextArea noticeArea;

    private ModeComBox modeSelect;

    private JButton clearBtn;

    private JLabel addressLabel;

    private JTextField serverAddress;

    private JButton startBtn;

    private JTextArea content;

    private JButton sendBtn;

    private int connectValue = 0;

    protected List<java.awt.Component> initComponent() {
        this.noticeArea = new JTextArea();
        this.noticeArea.setEditable(false);

        this.noticePane = new JScrollPane(this.noticeArea);
        this.noticePane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.noticePane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.modeSelect = new ModeComBox();
        this.modeSelect.clientInit(common);

        this.clearBtn = new JButton(common.getValue(ProjectCommon.clear));

        this.addressLabel = new JLabel(common.getValue(ProjectCommon.serverAddressDesc));
        this.serverAddress = new JTextField();
        this.startBtn = new JButton(common.getValue(ProjectCommon.connect));

        this.content = new JTextArea();
        this.sendBtn = new JButton(common.getValue(ProjectCommon.send));

        return Arrays.asList(noticePane, modeSelect, clearBtn, addressLabel, serverAddress,
                             startBtn, content, sendBtn);
    }

    protected void initListener() {

        modeSelect.addActionListener(e -> {
            ModeEvent modeEvent = new ModeEvent(ProjectCommon.clientMode);
            ModeItem modeItem = (ModeItem) modeSelect.getSelectedItem();
            if (modeItem==null) {
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
        SpringLayout springLayout = new SpringLayout();

        springLayout.putConstraint(SpringLayout.NORTH, noticePane, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, noticePane, 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, noticePane, -5, SpringLayout.EAST, this);

        springLayout.putConstraint(SpringLayout.SOUTH, noticePane, -10, SpringLayout.NORTH,
                                   modeSelect);

        springLayout.putConstraint(SpringLayout.WEST, modeSelect, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.WEST, clearBtn, 5, SpringLayout.EAST, modeSelect);

        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.NORTH,
                                   addressLabel);
        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.NORTH,
                                   serverAddress);
        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.NORTH,
                                   startBtn);

        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.NORTH,
                                   addressLabel);
        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.NORTH,
                                   serverAddress);
        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.NORTH, startBtn);

        springLayout.putConstraint(SpringLayout.WEST, addressLabel, 10, SpringLayout.WEST,
                                   this);
        springLayout.putConstraint(SpringLayout.WEST, serverAddress, 5, SpringLayout.EAST,
                                   addressLabel);
        springLayout.putConstraint(SpringLayout.EAST, serverAddress, -10, SpringLayout.WEST,
                                   startBtn);

        springLayout.putConstraint(SpringLayout.EAST, startBtn, -10, SpringLayout.EAST,
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
        springLayout.putConstraint(SpringLayout.EAST, content, -10, SpringLayout.WEST, sendBtn);
        springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, sendBtn, 0,
                                   SpringLayout.VERTICAL_CENTER, content);

        springLayout.putConstraint(SpringLayout.EAST, sendBtn, -10, SpringLayout.EAST, this);

        return springLayout;
    }


    public void reset() {
        modeSelect.removeAllItems();
        modeSelect.clientInit(common);
    }

    public void restStartBtn(){
        connectValue = 0;
        startBtn.setText(common.getValue(ProjectCommon.connect));
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
