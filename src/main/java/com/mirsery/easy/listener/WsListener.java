package com.mirsery.easy.listener;

import com.mirsery.easy.ProjectCommon;
import com.mirsery.easy.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WsListener {

    @Resource
    private Notice notice;

    @Resource
    private ProjectCommon common;

    @EventListener(OpenEvent.class)
    public void onOpen(OpenEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " " +
                common.getValue(ProjectCommon.connectServerSuccess));
    }


    @EventListener(CloseEvent.class)
    public void onClose(CloseEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " " + common.getValue(ProjectCommon.serverClose));
    }


    @EventListener(MessageEvent.class)
    public void onMessage(MessageEvent event) {

        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " " +
                common.getValue(ProjectCommon.receiveNotice)
                + " " + event.getMessage());
    }


    @EventListener(NotConnectedEvent.class)
    public void NotConnected(NotConnectedEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " " + common.getValue(ProjectCommon.noConnect));
    }

    /**
     * 数据发送
     ***/
    @EventListener(SendEvent.class)
    public void sendMessage(SendEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " " +
                common.getValue(ProjectCommon.sendNotice)
                + " " + event.getMessage());
    }

    public String getCurrentTime(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(timestamp));
    }

}
