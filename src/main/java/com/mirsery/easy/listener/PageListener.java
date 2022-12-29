package com.mirsery.easy.listener;

import com.mirsery.easy.event.page.ModeEvent;
import com.mirsery.easy.page.MainPanel;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Component
public class PageListener {


    @Resource
    private MainPanel mainPanel;

    @EventListener(ModeEvent.class)
    public void modeChange(ModeEvent event) {
        if (event.getTargetMode().equals(event.getSource().toString())) return;
        mainPanel.showMode(event.getTargetMode());
    }
}
