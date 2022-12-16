package com.mirsery.easy;

import com.mirsery.easy.bean.EasyClient;
import com.mirsery.easy.page.BodyJPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * easy-websocket
 *
 * @author ls
 * @date 2022/12/16
 */
@Configuration
public class ProjectConfig {

    @Bean
    public BodyJPanel bodyJPanel(@Autowired EasyClient easyClient, @Autowired MessageSource messageSource) {
        return new BodyJPanel(easyClient, messageSource);
    }
}
