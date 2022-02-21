package com.corekcioglu.blocking.exception.translation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import reactor.tools.agent.ReactorDebugAgent;

@EnableConfigurationProperties
@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
public class Application {

    public static void main(final String... args) {
        ReactorDebugAgent.init();
        SpringApplication.run(Application.class, args);
    }
}
