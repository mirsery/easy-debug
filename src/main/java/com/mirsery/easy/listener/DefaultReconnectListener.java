package com.mirsery.easy.listener;

import com.mirsery.easy.IotClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultReconnectListener {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void onReconnect(IotClient iotClient) {

        if (iotClient != null && iotClient.getState() == 0) {
            try {
                if (iotClient.isOpen()) return;
                iotClient.setState(1); //set reconnecting
                executorService.submit(() -> {
                    logger.info("reconnect to the server " + iotClient.getURI().getHost());
                    iotClient.reconnect();
                    iotClient.setState(0);//设置为normal
                });
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}