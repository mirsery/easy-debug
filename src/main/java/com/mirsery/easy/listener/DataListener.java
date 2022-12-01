package com.mirsery.easy.listener;


import com.mirsery.easy.IotClient;

public interface DataListener {

    /**
     * > iotClient may be null
     * **/
    public void onData(String message, IotClient iotClient);
}
