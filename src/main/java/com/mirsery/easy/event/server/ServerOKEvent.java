package com.mirsery.easy.event.server;

import org.springframework.context.ApplicationEvent;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/28
 */
public class ServerOKEvent extends ApplicationEvent {

    public ServerOKEvent(Object source) {
        super(source);
    }
}
