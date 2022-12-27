package com.mirsery.easy.page.panel;

import com.mirsery.easy.page.component.ClearBtn;
import com.mirsery.easy.page.component.ModeSelectBox;
import com.mirsery.easy.page.component.NoticeArea;
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
    private NoticeArea noticeArea;

    @Resource
    private ModeSelectBox modeSelect;

    @Resource
    private ClearBtn clearBtn;

    public void init() {

        this.lodLayout();
        this.loadComponent();
    }

    private void lodLayout() {
        SpringLayout springLayout = new SpringLayout();

        springLayout.putConstraint(SpringLayout.NORTH, noticeArea, 10, SpringLayout.NORTH, this);

        springLayout.putConstraint(SpringLayout.WEST, noticeArea, 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, noticeArea, -5, SpringLayout.EAST, this);

        springLayout.putConstraint(SpringLayout.SOUTH, noticeArea, -10, SpringLayout.NORTH, modeSelect);
        springLayout.putConstraint(SpringLayout.SOUTH, noticeArea, -10, SpringLayout.NORTH, clearBtn);


        springLayout.putConstraint(SpringLayout.WEST, modeSelect, 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.WEST, clearBtn, 20, SpringLayout.EAST, modeSelect);

        springLayout.putConstraint(SpringLayout.SOUTH, modeSelect, -10, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, clearBtn, -10, SpringLayout.SOUTH, this);

        this.setLayout(springLayout);
    }


    private void loadComponent() {
        this.add(noticeArea);
        this.add(modeSelect);
        this.add(clearBtn);
    }
}
