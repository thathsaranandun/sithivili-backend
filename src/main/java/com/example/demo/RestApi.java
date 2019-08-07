package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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

    @Scheduled(fixedRate = 60000, initialDelay = 300000)
    public void serverRunningLogger() {
        Logger.getLogger("server.status.indicator").info("Server running...");
    }
}
