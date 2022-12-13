package com.mirsery.easy.listener;

import com.mirsery.easy.page.BodyJPanel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Notice {

    @Resource
    private BodyJPanel bodyJPanel;

    public void recordMessage(String message) {
        bodyJPanel.recordMessage(message);
    }

}
