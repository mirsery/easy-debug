package com.mirsery.easy.event.page;

import org.springframework.context.ApplicationEvent;

public class NoticeEvent extends ApplicationEvent {

    public NoticeEvent(Object source) {
        super(source);
    }
}
