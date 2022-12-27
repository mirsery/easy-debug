package com.mirsery.easy.listener;

import com.mirsery.easy.page.panel.ClientModePanel;
import org.springframework.stereotype.Service;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Service
public class ClientPanelNotice implements ClientNotice {

    private ClientModePanel clientModePanel;

    @Override
    public void recordMessage(String message) {
        clientModePanel.appendText(message);
    }
}
