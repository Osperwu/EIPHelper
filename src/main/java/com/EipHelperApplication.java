package com;

import com.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Import(Config.class)
@EnableScheduling
public class EipHelperApplication {
    public static void main(String[] args) {
        SpringApplication.run(EipHelperApplication.class, args);
    }

}
