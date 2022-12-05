package com.mirsery.easy;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EasyWebsocketApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(
                EasyWebsocketApplication.class);
        ApplicationContext context = builder.headless(false).web(WebApplicationType.NONE).run(args);


    }
}
