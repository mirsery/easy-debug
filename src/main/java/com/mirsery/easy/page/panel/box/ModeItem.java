package com.mirsery.easy.page.panel.box;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
public class ModeItem {
    private final String value;

    private final String text;

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public ModeItem(String value, String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
