package com.mirsery.easy.page.panel;

import com.mirsery.easy.event.page.ModeType;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

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

    public void serverInit(MessageSource messageSource) {
        this.addItem(new ModeItem(ModeType.serverMode, messageSource.getMessage(ModeType.serverMode,
                null, LocaleContextHolder.getLocale())));
        this.addItem(new ModeItem(ModeType.clientMode, messageSource.getMessage(ModeType.clientMode,
                null, LocaleContextHolder.getLocale())));
    }

    public void clientInit(MessageSource messageSource) {
        this.addItem(new ModeItem(ModeType.clientMode, messageSource.getMessage(ModeType.clientMode,
                null, LocaleContextHolder.getLocale())));
        this.addItem(new ModeItem(ModeType.serverMode, messageSource.getMessage(ModeType.serverMode,
                null, LocaleContextHolder.getLocale())));

    }
}
