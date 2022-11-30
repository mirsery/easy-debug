package com.mirsery.easy;

import com.mirsery.easy.listener.CloseListener;
import com.mirsery.easy.listener.DataListener;
import com.mirsery.easy.listener.OpenListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public final class EasyClient {

    private static EasyClient easyClient = null;

    private URI uri;

    private List<DataListener> dataListeners;

    private CloseListener closeListener;

    private OpenListener openListener;

    private IotClient iotClient;

    private EasyClient() {
    }

    public synchronized static EasyClient getInstance() {
        if (easyClient==null) {
            easyClient = new EasyClient();
        }
        return easyClient;
    }

    public void setUrl(String url) throws URISyntaxException {
        easyClient.uri = new URI(url);
    }

    public void init(OpenListener openListener, CloseListener closeListener,
                     List<DataListener> dataListeners) {
        easyClient.openListener = openListener;
        easyClient.closeListener = closeListener;
        easyClient.dataListeners = dataListeners;
    }

    private void initListener(IotClient iotClient) {
        iotClient.setDataListeners(dataListeners);
        iotClient.setOpenListener(openListener);
        iotClient.setCloseListener(closeListener);
    }


    public synchronized void connect() throws InterruptedException {
        if (easyClient.iotClient==null) {
            easyClient.iotClient = new IotClient(uri);
        } else if (easyClient.iotClient.isOpen() && !easyClient.iotClient.getURI()
                .equals(this.uri)) {
            close();
            easyClient.iotClient = new IotClient(easyClient.uri);
        }
        this.initListener(easyClient.iotClient);
        easyClient.iotClient.connect();
    }

    public void send(String message) {
        if (easyClient.iotClient!=null) {
            easyClient.iotClient.send(message);
        }
    }


    /**
     * force close
     **/
    public synchronized void close() throws InterruptedException {
        if (easyClient.iotClient!=null && !easyClient.iotClient.isClosed()) {
            easyClient.iotClient.clearListener();
            easyClient.iotClient.closeBlocking();
            easyClient.iotClient = null;
        }

    }


}
