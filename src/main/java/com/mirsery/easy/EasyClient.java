package com.mirsery.easy;


import com.mirsery.easy.listener.CloseListener;
import com.mirsery.easy.listener.DataListener;
import com.mirsery.easy.listener.DefaultReconnectListener;
import com.mirsery.easy.listener.OpenListener;

import java.net.URI;
import java.net.URISyntaxException;

public final class EasyClient {

    private static EasyClient easyClient = null;

    private URI uri;

    private DataListener dataListener;

    private CloseListener closeListener;

    private OpenListener openListener;

    private DefaultReconnectListener reconnectListener;

    private IotClient iotClient;

    private EasyClient() {
    }

    public synchronized static EasyClient getInstance() {
        if (easyClient == null) {
            easyClient = new EasyClient();
        }
        return easyClient;
    }

    /*
     * change the uri should use connect func after.
     **/
    public void setUrl(String url) throws URISyntaxException {
        this.uri = new URI(url);
    }

    /**
     * only can be init once
     **/
    public void init(OpenListener openListener, CloseListener closeListener,
                     DataListener dataListener) {
        this.openListener = openListener;
        this.closeListener = closeListener;
        this.dataListener = dataListener;
        this.reconnectListener = new DefaultReconnectListener();
    }

    private void initListener(IotClient iotClient) {
        iotClient.setDataListener(dataListener);
        iotClient.setOpenListener(openListener);
        iotClient.setCloseListener(closeListener);
        iotClient.setReconnectListener(reconnectListener);
    }


    public synchronized void connect() throws InterruptedException {

        if (this.iotClient != null) {
            if (this.iotClient.isOpen()) {
                if (!this.iotClient.getURI().equals(this.uri)) {
                    close();
                    this.iotClient = null;
                } else {
                    return;
                }
            } else {
                close();
            }
        }
        this.iotClient = new IotClient(this.uri);
        this.initListener(this.iotClient);
        this.iotClient.connect();
    }

    public void send(String message) {
        if (this.iotClient != null) {
            this.iotClient.send(message);
        }
    }


    /**
     * Normal close
     **/
    public synchronized void close() throws InterruptedException {
        if (this.iotClient != null) {
            this.iotClient.clearListener();
            if (!this.iotClient.isClosed()) {
                this.iotClient.closeBlocking();
            }
        }
        this.iotClient = null;
    }


}
