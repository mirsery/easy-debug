package com.mirsery.easy.listener;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
public interface ServerNotice {
    void recordMessage(String message);

    void addClient(String remoteAddr);

    void removeClient(String remoteAddr);
}
