package com.mirsery.easy.page.panel;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/28
 */
public class ClientItem {
    private final String text;

    public int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ClientItem(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
