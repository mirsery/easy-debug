package com.mirsery.easy;

import com.mirsery.easy.bean.IotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class ProjectConfig {


    @Bean
    public IotClient iotClient(@Autowired ApplicationEventPublisher applicationEventPublisher) throws URISyntaxException {
        IotClient iotClient = new IotClient(new URI("ws://127.0.0.1"));
        iotClient.setApplicationEventPublisher(applicationEventPublisher);
        return iotClient;
    }

}
