package com.mirsery.easy.listener;


import com.mirsery.easy.IotClient;

public interface OpenListener {

    /**
     * > iotClient may be null
     **/
    public void onOpen(IotClient iotClient);
}
