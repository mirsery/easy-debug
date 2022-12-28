package com.mirsery.easy.bean.server;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/28
 */
@Component
public class EasyServer {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private WsClientManager wsClientManager;


    private IotServer iotServer;

    private int port;

    public void setPort(int port) {
        this.port = port;
    }

    public synchronized void connect() throws InterruptedException {

        if (this.iotServer != null) {
            if (this.iotServer.getIsOK() == 1) {
                if (this.iotServer.getAddress().getPort() != this.port) {
                    close();
                    this.iotServer = null;
                } else {
                    return;
                }
            } else {
                close();
            }
        }
        this.iotServer = new IotServer(new InetSocketAddress(this.port));
        this.initApplicationEventPublisher(this.iotServer);
        this.iotServer.start();
    }

    public void send(String remoteAddr, String message) {
        if (this.iotServer != null) {
            this.iotServer.send(remoteAddr, message);
        }
    }

    public void sendALl(String message) {
        if (this.wsClientManager != null) {
            wsClientManager.getClientKeys().forEach(key -> {
                send(key, message);
            });
        }
    }


    /**
     * Normal close
     **/
    public synchronized void close() throws InterruptedException {
        if (this.iotServer != null) {
            this.iotServer.clear();
            this.iotServer.stop();
        }
        this.iotServer = null;
    }

    public void initApplicationEventPublisher(IotServer iotServer) {
        iotServer.setApplicationEventPublisher(applicationEventPublisher);
        iotServer.setClientManager(wsClientManager);
    }

    public void disconnect(String remoteAddr) {
        if (this.iotServer != null) {
            this.iotServer.disconnect(remoteAddr);
        }
    }

}
