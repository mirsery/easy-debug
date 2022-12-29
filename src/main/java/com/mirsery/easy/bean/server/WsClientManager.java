package com.mirsery.easy.bean.server;

import org.java_websocket.WebSocket;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/28
 */
@Component
public final class WsClientManager {

    private final ConcurrentHashMap<String, WebSocket> clients = new ConcurrentHashMap<>();


    public void addClient(WebSocket conn) {
        if (conn!=null && conn.getRemoteSocketAddress()!=null) {
            clients.put(conn.getRemoteSocketAddress().toString(), conn);
        }
    }

    public void removeClient(WebSocket conn) {
        if (conn!=null && conn.getRemoteSocketAddress()!=null) {
            clients.remove(conn.getRemoteSocketAddress().toString());
        }
    }

    public void send(String remoteAddr, String message) throws WebsocketNotConnectedException {
        WebSocket webSocket = clients.get(remoteAddr);
        if (webSocket!=null) {
            webSocket.send(message);
        }
    }

    public void disconnect(String remoteAddr) {
        WebSocket webSocket = clients.get(remoteAddr);
        if (webSocket!=null) {
            webSocket.close();
        }
        clients.remove(remoteAddr);
    }

    public Collection<WebSocket> getClients() {
        return clients.values();
    }


    public Collection<String> getClientKeys() {
        return clients.keySet();
    }
}
