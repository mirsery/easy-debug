package com.mirsery.easy.event.ws;

import org.springframework.context.ApplicationEvent;

public class NotConnectedEvent extends ApplicationEvent {
    public NotConnectedEvent(Object source) {
        super(source);
    }
}
