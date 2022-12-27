package com.mirsery.easy.listener;

import com.mirsery.easy.page.BodyJPanel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Service
public class BodyPanelNotice implements ClientNotice {

    @Resource
    private BodyJPanel bodyPanel;

    @Override
    public void recordMessage(String message) {
        bodyPanel.recordMessage(message);
    }
}
