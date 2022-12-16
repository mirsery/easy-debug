package com.mirsery.easy.listener;

import com.mirsery.easy.event.*;
import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WsListener {

    @Resource
    private Notice notice;

    @Resource
    private MessageSource messageSource;

    @EventListener(OpenEvent.class)
    public void onOpen(OpenEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " " +
                messageSource.getMessage("connectServerSuccess", null, LocaleContextHolder.getLocale()));
    }


    @EventListener(CloseEvent.class)
    public void onClose(CloseEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " " + messageSource.getMessage("serverClose", null, LocaleContextHolder.getLocale()));
    }


    @EventListener(MessageEvent.class)
    public void onMessage(MessageEvent event) {

        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " " +
                messageSource.getMessage("receiveNotice", null, LocaleContextHolder.getLocale())
                + " " + event.getMessage());
    }


    @EventListener(NotConnectedEvent.class)
    public void NotConnected(NotConnectedEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " " + messageSource.getMessage("noConnect", null, LocaleContextHolder.getLocale()));
    }

    /**
     * 数据发送
     ***/
    @EventListener(SendEvent.class)
    public void sendMessage(SendEvent event) {
        notice.recordMessage(getCurrentTime(event.getTimestamp()) + " " +
                messageSource.getMessage("sendNotice", null, LocaleContextHolder.getLocale())
                + " " + event.getMessage());
    }

    public String getCurrentTime(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(timestamp));
    }

}
