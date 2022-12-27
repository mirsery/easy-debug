package com.mirsery.easy.event.page;

import org.springframework.context.ApplicationEvent;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
public class ModeEvent extends ApplicationEvent {

    private String targetMode;

    public ModeEvent(Object source) {
        super(source);
    }


    public String getTargetMode() {
        return targetMode;
    }

    public void setTargetMode(String targetMode) {
        this.targetMode = targetMode;
    }
}
