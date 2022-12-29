package com.mirsery.easy.listener;

import com.mirsery.easy.ProjectCommon;
import com.mirsery.easy.event.client.*;
import com.mirsery.easy.notice.ClientNotice;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class WsClientListener {

    @Resource
    private ProjectCommon common;

    @Resource
    private List<ClientNotice> notices;

    @EventListener(OpenEvent.class)
    public void onOpen(OpenEvent event) {
        notices.forEach(notice -> notice.recordMessage(
                getCurrentTime(event.getTimestamp()) + " " +
                        common.getValue(ProjectCommon.connectServerSuccess)));
    }


    @EventListener(CloseEvent.class)
    public void onClose(CloseEvent event) {
        notices.forEach(notice -> {
            notice.recordMessage(getCurrentTime(event.getTimestamp()) + " " +
                                               common.getValue(ProjectCommon.serverClose));
            notice.resetConnectState();
        });


    }


    @EventListener(MessageEvent.class)
    public void onMessage(MessageEvent event) {

        notices.forEach(notice -> notice.recordMessage(
                getCurrentTime(event.getTimestamp()) + " " +
                        common.getValue(ProjectCommon.receiveNotice)
                        + " " + event.getMessage()));


    }


    @EventListener(NotConnectedEvent.class)
    public void NotConnected(NotConnectedEvent event) {
        notices.forEach(notice -> notice.recordMessage(
                getCurrentTime(event.getTimestamp()) + " " +
                        common.getValue(ProjectCommon.noConnect)));
    }

    /**
     * 数据发送
     ***/
    @EventListener(SendEvent.class)
    public void sendMessage(SendEvent event) {
        notices.forEach(notice -> notice.recordMessage(
                getCurrentTime(event.getTimestamp()) + " " +
                        common.getValue(ProjectCommon.sendNotice)
                        + " " + event.getMessage()));
    }

    public String getCurrentTime(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(timestamp));
    }

}
