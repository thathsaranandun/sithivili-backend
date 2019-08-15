package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class RestApi {

    public static void main(String[] args) {
        SpringApplication.run(RestApi.class, args);
        Logger.getLogger("server.status.indicator")
                .info("Server started @" +
                        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
    }

}
