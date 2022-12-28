package com.mirsery.easy.event.server;

import org.springframework.context.ApplicationEvent;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/28
 */
public class ClientLogoutEvent extends ApplicationEvent {

    private String remoteAddr;

    public ClientLogoutEvent(Object source) {
        super(source);
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
}
