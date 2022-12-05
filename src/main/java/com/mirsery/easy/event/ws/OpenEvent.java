package com.mirsery.easy.event.ws;

import org.springframework.context.ApplicationEvent;

/**
 * 连接建立事件
 **/
public class OpenEvent extends ApplicationEvent {

    public OpenEvent(Object source) {
        super(source);
    }
}
