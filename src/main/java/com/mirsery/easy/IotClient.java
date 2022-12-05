package com.mirsery.easy;


import java.net.URI;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

public class IotClient extends WebSocketClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationEventPublisher applicationEventPublisher;

    public IotClient(URI serverUri) {
        super(serverUri);
    }


    public void setURI(URI url) {
        // 修改url
        this.uri = url;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {

    }

    public void send(String message) {
        try {
            super.send(message);
        } catch (WebsocketNotConnectedException ws) {

        }
    }


    @Override
    public void onMessage(String message) {

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {
    }

}
