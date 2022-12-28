package com.mirsery.easy.event.client;

import org.springframework.context.ApplicationEvent;

public class SendEvent extends ApplicationEvent {

    private String message;

    public SendEvent(Object source) {
        super(source);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
