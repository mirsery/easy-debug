package com.mirsery.easy.bean.client;


import com.mirsery.easy.event.client.*;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import java.net.URI;

public final class IotClient extends WebSocketClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationEventPublisher applicationEventPublisher;

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public IotClient(URI serverUri) {
        super(serverUri);
    }


    public void setURI(URI url) {
        // 修改url
        this.uri = url;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        if (applicationEventPublisher == null) return;
        applicationEventPublisher.publishEvent(new OpenEvent("[OnOpen]"));
    }

    public void send(String message) {
        if (applicationEventPublisher == null) return;
        try {
            super.send(message);
            SendEvent sendEvent = new SendEvent("[send]");
            sendEvent.setMessage(message);
            applicationEventPublisher.publishEvent(sendEvent);
        } catch (WebsocketNotConnectedException ws) {
            applicationEventPublisher.publishEvent(new NotConnectedEvent("[send]"));
        }
    }


    @Override
    public void onMessage(String message) {
        if (applicationEventPublisher == null) return;
        MessageEvent messageEvent = new MessageEvent("[onMessage]");
        messageEvent.setMessage(message);
        applicationEventPublisher.publishEvent(messageEvent);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        if (applicationEventPublisher == null) return;
        applicationEventPublisher.publishEvent(new CloseEvent("[onClose]"));
    }

    @Override
    public void onError(Exception ex) {
        //TODO
    }

    public synchronized void clear() {
        applicationEventPublisher = null;
    }

}
