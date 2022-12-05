package com.mirsery.easy.listener.page;

import com.mirsery.easy.event.page.NoticeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PageListener {

    @EventListener(NoticeEvent.class)
    public void onNotice(NoticeEvent event) {

    }
}
