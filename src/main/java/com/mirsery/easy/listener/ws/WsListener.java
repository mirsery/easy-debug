package com.mirsery.easy.listener.ws;

import com.mirsery.easy.event.ws.CloseEvent;
import com.mirsery.easy.event.ws.MessageEvent;
import com.mirsery.easy.event.ws.OpenEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class WsListener {

    @EventListener(OpenEvent.class)
    public void onOpen(OpenEvent event) {

    }


    @EventListener(CloseEvent.class)
    public void onClose(CloseEvent event) {

    }


    @EventListener(MessageEvent.class)
    public void onMessage(MessageEvent event) {

    }

}
