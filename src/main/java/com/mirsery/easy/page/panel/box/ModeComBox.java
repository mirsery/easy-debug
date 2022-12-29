package com.mirsery.easy.page.panel.box;

import com.mirsery.easy.ProjectCommon;

import javax.swing.*;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
public class ModeComBox extends JComboBox<ModeItem> {

    public ModeComBox() {
        super();
    }

    public void serverInit(ProjectCommon common) {
        this.addItem(new ModeItem(ProjectCommon.serverMode, common.getValue(ProjectCommon.serverMode)));
        this.addItem(new ModeItem(ProjectCommon.clientMode, common.getValue(ProjectCommon.clientMode)));

    }

    public void clientInit(ProjectCommon common) {
        this.addItem(new ModeItem(ProjectCommon.clientMode, common.getValue(ProjectCommon.clientMode)));
        this.addItem(new ModeItem(ProjectCommon.serverMode, common.getValue(ProjectCommon.serverMode)));
    }
}
