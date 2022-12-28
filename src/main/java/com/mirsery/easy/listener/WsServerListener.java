package com.mirsery.easy.listener;

import com.mirsery.easy.ProjectCommon;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/27
 */
@Component
public class WsServerListener {

    @Resource
    private ProjectCommon common;

    @Resource
    private List<ServerNotice> notices;
}
