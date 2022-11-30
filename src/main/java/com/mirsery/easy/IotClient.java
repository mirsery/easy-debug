package com.mirsery.easy;

import com.mirsery.easy.listener.CloseListener;
import com.mirsery.easy.listener.DataListener;
import com.mirsery.easy.listener.OpenListener;
import com.mirsery.easy.listener.ReconnectEvent;
import java.net.URI;
import java.util.List;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class IotClient extends WebSocketClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public IotClient(URI serverUri) {
        super(serverUri);
    }

    private List<DataListener> dataListeners = null;

    private CloseListener closeListener = null;

    private OpenListener openListener = null;

    private ReconnectEvent reconnectListener = null;

    public void setDataListeners(List<DataListener> dataListeners) {
        this.dataListeners = dataListeners;
    }

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }

    public void setOpenListener(OpenListener openListener) {
        this.openListener = openListener;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        if (openListener!=null) {
            openListener.onOpen(this);
        }
    }

    public void send(String message) {
        try {
            super.send(message);
        } catch (WebsocketNotConnectedException ws) {
            reconnectListener.onReconnect(this);
        }
    }

    @Override
    public void onMessage(String message) {
        logger.info("[read] " + message);
        if (dataListeners==null) {
            return;
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

        if (reconnectListener!=null) {
            reconnectListener.onReconnect(this);
        }
        if (closeListener==null) {
            return;
        }
    }

    @Override
    public void onError(Exception ex) {
        logger.error(ex.getMessage());
    }

    public synchronized void clearListener() {
        dataListeners = null;
        closeListener = null;
        openListener = null;
        reconnectListener = null;
    }
}
