package com.mirsery.easy.event.server;

import org.springframework.context.ApplicationEvent;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/28
 */
public class ClientMessageEvent extends ApplicationEvent {
    private String remoteAddr;

    private String message;

    public ClientMessageEvent(Object source) {
        super(source);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
}
