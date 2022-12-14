package com.mirsery.easy.bean;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * easy-websocket
 *
 * @date 2022/12/14
 */
@Component
public class EasyClient {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    private IotClient wsClient;

    private URI uri;

    public void setUrl(String url) throws URISyntaxException {
        this.uri = new URI(url);
    }

    public synchronized void connect() throws InterruptedException {

        if (this.wsClient != null) {
            if (this.wsClient.isOpen()) {
                if (!this.wsClient.getURI().equals(this.uri)) {
                    close();
                    this.wsClient = null;
                } else {
                    return;
                }
            } else {
                close();
            }
        }
        this.wsClient = new IotClient(this.uri);
        this.initApplicationEventPublisher(this.wsClient);
        this.wsClient.connect();
    }

    public void send(String message) {
        if (this.wsClient != null) {
            this.wsClient.send(message);
        }
    }


    /**
     * Normal close
     **/
    public synchronized void close() throws InterruptedException {
        if (this.wsClient != null) {
            this.wsClient.clear();
            if (!this.wsClient.isClosed()) {
                this.wsClient.closeBlocking();
            }
        }
        this.wsClient = null;
    }

    public void initApplicationEventPublisher(IotClient iotClient) {
        iotClient.setApplicationEventPublisher(applicationEventPublisher);
    }


}
