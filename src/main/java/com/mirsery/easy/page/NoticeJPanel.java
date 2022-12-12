package com.mirsery.easy.page;

import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

@Component
public class NoticeJPanel extends JPanel {
    private final JTextArea area = new JTextArea();

    public NoticeJPanel() {

        FontUIResource fontRes = new FontUIResource(new Font("alias", Font.PLAIN, 20));

        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
        area.setEditable(false); // 设置输入框允许编辑
        area.setPreferredSize(new Dimension(880,400));
//        area.setColumns(44); // 设置输入框的长度为14个字符
//        area.setRows(13); // 设置输入框的高度为3行字符
        area.setLineWrap(true); // 设置每行是否折叠。为true的话，输入字符超过每行宽度就会自动换行
        // 因为下面添加滚动条的时候，滚动条已经关联了JTextArea，所以这里不必单独添加多行输入框
        JScrollPane scroll = new JScrollPane(area); // 创建一个滚动条
        this.add(scroll); // 在面板上添加滚动条
    }

    public void recordMessage(String message) {
        this.area.append(message + "\n");
    }

    public void clearMessage() {
        this.area.setText("");
    }

}
