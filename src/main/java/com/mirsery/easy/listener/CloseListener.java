package com.mirsery.easy.listener;

public interface CloseListener {
    public void onClose(int code, String reason, boolean remote);
}
