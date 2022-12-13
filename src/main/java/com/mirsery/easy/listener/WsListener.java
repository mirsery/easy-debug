package com.mirsery.easy.listener;

import com.mirsery.easy.event.ws.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WsListener {

    @Resource
    private Notice notice;

    @EventListener(OpenEvent.class)
    public void onOpen(OpenEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " connect to the server success.");
    }


    @EventListener(CloseEvent.class)
    public void onClose(CloseEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " server close the channel.");
    }


    @EventListener(MessageEvent.class)
    public void onMessage(MessageEvent event) {

        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " [receive] " + event.getMessage());
    }


    @EventListener(NotConnectedEvent.class)
    public void NotConnected(NotConnectedEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " not connect the server.");
    }

    /**
     * 数据发送
     ***/
    @EventListener(SendEvent.class)
    public void sendMessage(SendEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " [send] " + event.getMessage());
    }

    public String getCurrentTime(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(timestamp));
    }

}
