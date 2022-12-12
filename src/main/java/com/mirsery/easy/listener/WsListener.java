package com.mirsery.easy.listener;

import com.mirsery.easy.bean.IotClient;
import com.mirsery.easy.event.ws.*;
import com.mirsery.easy.page.NoticeJPanel;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WsListener {

    @Resource
    private IotClient iotClient;

    @Resource
    private NoticeJPanel noticeJPanel;

    @EventListener(OpenEvent.class)
    public void onOpen(OpenEvent event) {
        noticeJPanel.recordMessage(getCurrentTime(event.getTimestamp()) + " connect to the server success.");
    }


    @EventListener(CloseEvent.class)
    public void onClose(CloseEvent event) {
        noticeJPanel.recordMessage(getCurrentTime(event.getTimestamp()) + " server close the channel.");
    }


    @EventListener(MessageEvent.class)
    public void onMessage(MessageEvent event) {

        noticeJPanel.recordMessage(getCurrentTime(event.getTimestamp()) + " [receive] " + event.getMessage());
    }


    @EventListener(NotConnectedEvent.class)
    public void NotConnected(NotConnectedEvent event) {
        noticeJPanel.recordMessage(getCurrentTime(event.getTimestamp()) + " not connect the server.");
    }

    /**
     * 数据发送
     ***/
    @EventListener(SendEvent.class)
    public void sendMessage(SendEvent event) {
        iotClient.send(event.getMessage());
        noticeJPanel.recordMessage(getCurrentTime(event.getTimestamp()) + " [send] " + event.getMessage());
    }

    public String getCurrentTime(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(timestamp));
    }

}
