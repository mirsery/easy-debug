package com.mirsery.easy.event.ws;

import org.springframework.context.ApplicationEvent;


/**
 * 消息事件（包含收和发)
 **/
public class MessageEvent extends ApplicationEvent {

    public MessageEvent(Object source) {
        super(source);
    }
}
