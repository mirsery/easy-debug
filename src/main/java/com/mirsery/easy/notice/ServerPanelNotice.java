package com.mirsery.easy.notice;

import com.mirsery.easy.page.panel.ServerModePanel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Service
public class ServerPanelNotice implements ServerNotice {

    @Resource
    private ServerModePanel serverModePanel;

    @Override
    public void recordMessage(String message) {
        serverModePanel.recordMessage(message);
    }


    public void addClient(String remoteAddr) {
        serverModePanel.addItem(remoteAddr);
    }

    public void removeClient(String remoteAddr) {
        serverModePanel.removeItem(remoteAddr);
    }
}
