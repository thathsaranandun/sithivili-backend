package com.skepseis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableJpaRepositories(basePackages = {"com.skepseis.*"})
@ComponentScan(basePackages = { "com.skepseis.*"})
@EntityScan("com.skepseis.*")
@PropertySource("classpath:/service.properties")
public class RestApi {


    public static void main(String[] args) {


        SpringApplication.run(RestApi.class, args);
        Logger.getLogger("server.status.indicator")
                .info("Server started @" +
                        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));

    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
