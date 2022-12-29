package com.mirsery.easy.bean.server;

import com.mirsery.easy.event.server.*;
import org.java_websocket.WebSocket;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.context.ApplicationEventPublisher;

import java.net.InetSocketAddress;

public final class IotServer extends WebSocketServer {


    private WsClientManager clientManager;

    private ApplicationEventPublisher applicationEventPublisher;

    private int isOK = 0;


    public IotServer(InetSocketAddress address) {
        super(address);
    }

    public void setClientManager(WsClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        if (this.clientManager != null) {
            this.clientManager.addClient(conn);
            ClientLoginEvent event = new ClientLoginEvent("[onOpen]");
            event.setRemoteAddr(conn.getRemoteSocketAddress().toString());
            this.applicationEventPublisher.publishEvent(event);
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        if (this.clientManager != null) {
            this.clientManager.removeClient(conn);
            ClientLogoutEvent event = new ClientLogoutEvent("[onClose]");
            event.setRemoteAddr(conn.getRemoteSocketAddress().toString());
            this.applicationEventPublisher.publishEvent(event);
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        if (this.clientManager != null) {

            ClientMessageEvent event = new ClientMessageEvent("[onMessage]");
            event.setRemoteAddr(conn.getRemoteSocketAddress().toString());
            event.setMessage(message);

            this.applicationEventPublisher.publishEvent(event);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        if (isOK == 0) {
            ErrorEvent event = new ErrorEvent("[onError]");
            event.setError(ex.getMessage());
            this.applicationEventPublisher.publishEvent(event);
        }
    }

    @Override
    public void onStart() {
        isOK = 1;
        this.applicationEventPublisher.publishEvent(new ServerOKEvent("[onStart]"));
    }


    public void send(String remoteAddr, String message) {
        if (clientManager != null) {
            try {
                clientManager.send(remoteAddr, message);
                ServerSendEvent event = new ServerSendEvent("[send]");
                event.setRemoteAddr(remoteAddr);
                event.setMessage(message);
                this.applicationEventPublisher.publishEvent(event);
            } catch (WebsocketNotConnectedException e) {
                ErrorEvent event = new ErrorEvent("[send]");
                event.setError(e.getMessage());
                this.applicationEventPublisher.publishEvent(event);
            }

        }
    }

    public void disconnect(String remoteAddr) {
        if (clientManager != null) {
            clientManager.disconnect(remoteAddr);
            DisconnectEvent event = new DisconnectEvent("[disconnect]");
            event.setRemoteAddr(remoteAddr);
            this.applicationEventPublisher.publishEvent(event);
        }
    }

    public void disconnectAll() {
        if (clientManager != null)
            clientManager.getClientKeys().forEach(this::disconnect);
    }

    public int getIsOK() {
        return isOK;
    }

    public synchronized void clear() {
        isOK = 0;
        disconnectAll();
        clientManager = null;
        applicationEventPublisher = null;
    }


}
