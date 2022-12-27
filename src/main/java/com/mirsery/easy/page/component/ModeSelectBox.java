package com.mirsery.easy.page.component;

import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * easy-websocket
 *
 * @author ls
 * @date 2022/12/27
 */
@Component
public class ModeSelectBox extends JComboBox<String> {

    public ModeSelectBox() {
        this.addItem("客户端模式");
        this.addItem("服务端模式");
    }


}
