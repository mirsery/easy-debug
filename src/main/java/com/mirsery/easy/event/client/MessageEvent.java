package com.mirsery.easy.event.client;

import org.springframework.context.ApplicationEvent;


/**
 * 消息事件
 **/
public class MessageEvent extends ApplicationEvent {

    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageEvent(Object source) {
        super(source);
    }


}
