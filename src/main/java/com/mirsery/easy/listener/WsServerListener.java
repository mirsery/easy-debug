package com.mirsery.easy.listener;

import com.mirsery.easy.ProjectCommon;
import com.mirsery.easy.event.server.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Component
public class WsServerListener {

    @Resource
    private ProjectCommon common;

    @Resource
    private List<ServerNotice> notices;

    @EventListener(ClientLoginEvent.class)
    public void onOpen(ClientLoginEvent event) {
        notices.forEach(clientNotice -> {
            clientNotice.recordMessage(getCurrentTime(event.getTimestamp()) + " [" + event.getRemoteAddr() + "]" +
                    common.getValue(ProjectCommon.connect));

            clientNotice.addClient(event.getRemoteAddr());
        });
    }


    @EventListener(ClientLogoutEvent.class)
    public void onClose(ClientLogoutEvent event) {
        notices.forEach(clientNotice -> {

            clientNotice.recordMessage(getCurrentTime(event.getTimestamp()) + " [" + event.getRemoteAddr() + "]" +
                    common.getValue(ProjectCommon.disconnect));

            clientNotice.removeClient(event.getRemoteAddr());
        });

    }


    @EventListener(ClientMessageEvent.class)
    public void onMessage(ClientMessageEvent event) {

        notices.forEach(clientNotice -> clientNotice.recordMessage(getCurrentTime(event.getTimestamp()) + " [" + event.getRemoteAddr() + "]" +
                common.getValue(ProjectCommon.receiveNotice)
                + " " + event.getMessage()));


    }

    @EventListener(DisconnectEvent.class)
    public void disconnect(DisconnectEvent event) {
        notices.forEach(clientNotice -> {
            clientNotice.recordMessage(getCurrentTime(event.getTimestamp()) + " [" + event.getRemoteAddr() + "]" +
                    common.getValue(ProjectCommon.disconnect));
            clientNotice.removeClient(event.getRemoteAddr());
        });
    }


    @EventListener(ErrorEvent.class)
    public void NotBind(ErrorEvent event) {
        notices.forEach(clientNotice -> clientNotice.recordMessage(getCurrentTime(event.getTimestamp()) + " " +
                common.getValue(ProjectCommon.error) + event.getError()));
    }


    @EventListener(ServerOKEvent.class)
    public void serverOK(ServerOKEvent event) {
        notices.forEach(clientNotice -> clientNotice.recordMessage(getCurrentTime(event.getTimestamp()) +
                common.getValue(ProjectCommon.success)));

    }


    @EventListener(ServerSendEvent.class)
    public void sendMessage(ServerSendEvent event) {
        notices.forEach(clientNotice -> clientNotice.recordMessage(getCurrentTime(event.getTimestamp()) + " [" + event.getRemoteAddr() + "]" +
                common.getValue(ProjectCommon.sendNotice)
                + " " + event.getMessage()));
    }

    public String getCurrentTime(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(timestamp));
    }
}
