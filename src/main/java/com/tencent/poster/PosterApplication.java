package com.tencent.poster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tencent")
public class PosterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PosterApplication.class, args);
    }

}
