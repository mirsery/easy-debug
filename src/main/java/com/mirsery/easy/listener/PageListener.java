package com.mirsery.easy.listener;

import com.mirsery.easy.event.page.ModeEvent;
import com.mirsery.easy.page.panel.CardPanel;
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
    private CardPanel cardPanel;

    @EventListener(ModeEvent.class)
    public void modeChange(ModeEvent event) {
        if (event.getTargetMode().equals(event.getSource().toString())) return;
        cardPanel.showMode(event.getTargetMode());
    }
}
