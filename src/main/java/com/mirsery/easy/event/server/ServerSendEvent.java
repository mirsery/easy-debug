package com.mirsery.easy.event.server;

import org.springframework.context.ApplicationEvent;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/28
 */
public class ServerSendEvent extends ApplicationEvent {

    private String remoteAddr;

    private String message;

    public ServerSendEvent(Object source) {
        super(source);
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
