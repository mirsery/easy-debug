package com.mirsery.easy.notice;

import com.mirsery.easy.page.panel.ClientModePanel;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

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

    @Override
    public void resetConnectState() {
        clientModePanel.restStartBtn();
    }
}
