package com.mirsery.easy.page.easy;

import java.awt.Component;
import java.awt.LayoutManager;
import java.util.List;
import javax.swing.JPanel;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/29
 */
public abstract class EasyPanel extends JPanel {

    /**
     * 按照顺序返回需要被加载到画布中的组件
     * */
    protected abstract List<Component> initComponent();

    protected abstract LayoutManager lodLayout();

    protected abstract void initListener();

    /**
     * 初始化额外的配置和参数 hook
     * */
    protected abstract void initPanelConfig();

    public void init() {

        loadComponent();

        this.setLayout(lodLayout());

        initListener();

        initPanelConfig();
    }

    private void loadComponent() {
        List<Component> components = initComponent();
        if (components==null) {
            return;
        }
        components.forEach(this::add);
    }


}
