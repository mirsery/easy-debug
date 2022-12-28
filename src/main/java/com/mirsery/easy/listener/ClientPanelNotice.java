package com.mirsery.easy.listener;

import com.mirsery.easy.page.panel.ClientModePanel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Service
public class ClientPanelNotice implements ClientNotice {

    @Resource
    private ClientModePanel clientModePanel;

    @Override
    public void recordMessage(String message) {
        clientModePanel.recordMessage(message);
    }
}
