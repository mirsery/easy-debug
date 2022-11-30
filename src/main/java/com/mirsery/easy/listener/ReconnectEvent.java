package com.mirsery.easy.listener;

import com.mirsery.easy.IotClient;

public interface ReconnectEvent {


    public void onReconnect(IotClient iotClient);
}
