package com.mirsery.easy;

import com.mirsery.easy.bean.client.EasyClient;
import com.mirsery.easy.page.BodyJPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * easy-websocket
 *
 * @author mirsery
 * @date 2022/12/16
 */
@Configuration
public class ProjectConfig {

    @Bean
    public BodyJPanel bodyJPanel(@Autowired EasyClient easyClient, @Autowired ProjectCommon common) {
        return new BodyJPanel(easyClient, common);
    }
}
