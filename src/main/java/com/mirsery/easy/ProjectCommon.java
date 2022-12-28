package com.mirsery.easy;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Component
public class ProjectCommon {

    public static String title = "title";
    public static String serverAddressDesc = "serverAddressDesc";
    public static String connect = "connect";
    public static String disconnect = "disconnect";
    public static String send = "send";
    public static String clear = "clear";
    public static String connectServerSuccess = "connectServerSuccess";
    public static String serverClose = "serverClose";
    public static String receiveNotice = "receiveNotice";
    public static String noConnect = "noConnect";
    public static String sendNotice = "sendNotice";
    public static String serverMode = "serverMode";
    public static String clientMode = "clientMode";

    public static String start = "start";

    public static String stop = "stop";

    public static String localPort = "localPort";

    public static String client = "client";

    public static String allConnects = "allConnects";

    public static String error = "error";

    public static String urlError = "urlError";

    public static String success = "success";


    @Resource
    private MessageSource messageSource;

    public String getValue(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}
