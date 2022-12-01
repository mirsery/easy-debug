package com.mirsery.easy;


import com.mirsery.easy.listener.CloseListener;
import com.mirsery.easy.listener.DataListener;
import com.mirsery.easy.listener.DefaultReconnectListener;
import com.mirsery.easy.listener.OpenListener;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;


public final class IotClient extends WebSocketClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public IotClient(URI serverUri) {
        super(serverUri);
    }

    /**
     * 0 normal
     * 1 reconnecting
     */
    private int state = 0;

    private DataListener dataListener = null;

    private CloseListener closeListener = null;

    private OpenListener openListener = null;

    private DefaultReconnectListener reconnectListener = null;

    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }

    public void setReconnectListener(DefaultReconnectListener reconnectListener) {
        this.reconnectListener = reconnectListener;
    }

    public void setOpenListener(OpenListener openListener) {
        this.openListener = openListener;
    }

    public int getState() {
        return state;
    }

    public synchronized void setState(int state) {
        this.state = state;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        if (openListener != null) {
            openListener.onOpen(this);
        }
    }

    public void send(String message) {
        try {
            super.send(message);
        } catch (WebsocketNotConnectedException ws) {
            this.doReconnect();
        }
    }

    private void doReconnect() {
        if (reconnectListener != null) {
            reconnectListener.onReconnect(this);
        }
    }

    @Override
    public void onMessage(String message) {
        if (dataListener != null) {
            dataListener.onData(message, this);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        this.doReconnect();
        if (closeListener != null) {
            closeListener.onClose(code, reason, remote);
        }
    }

    @Override
    public void onError(Exception ex) {
//        logger.error(ex.getMessage());
    }

    public synchronized void clearListener() {
        dataListener = null;
        closeListener = null;
        openListener = null;
        reconnectListener = null;
    }
}
