package com.mirsery.easy.page.easy;


import javax.swing.*;
import java.awt.*;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/30
 */
public class EasySpringLayout {

    private final SpringLayout springLayout = new SpringLayout();

    public SpringLayout getSpringLayout() {
        return springLayout;
    }

    public void addAutoRowComponent(java.awt.Component container, int interval, int autoIndex, java.awt.Component... component) {
        rowHead(component[0], container);
        if (autoIndex + 1 < component.length) {
            springLayout.putConstraint(SpringLayout.EAST, component[autoIndex], -1 * interval, SpringLayout.WEST, component[autoIndex + 1]);
        }
        for (int i = 0; i + 1 < component.length; i++) {
            if (i != autoIndex) {
                springLayout.putConstraint(SpringLayout.WEST, component[i + 1], interval, SpringLayout.EAST, component[i]);
            }
        }
        springLayout.putConstraint(SpringLayout.EAST, component[component.length - 1], -5, SpringLayout.EAST, container);
    }

    public void addRowComponent(java.awt.Component container, int interval, java.awt.Component... component) {
        //interval default's value is 5.
        rowHead(component[0], container);
        for (int i = 0; i + 1 < component.length; i++) {
            springLayout.putConstraint(SpringLayout.WEST, component[i + 1], interval, SpringLayout.EAST, component[i]);
        }
    }

    private void rowHead(Component component, Component container) {
        springLayout.putConstraint(SpringLayout.WEST, component, 5, SpringLayout.WEST, container);
    }

    public void rowVerticalCenter(java.awt.Component... component) {
        for (int i = 0; i + 1 < component.length; i++) {
            springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, component[i], 0, SpringLayout.VERTICAL_CENTER, component[i + 1]);
        }
    }
}
