package com.djsenglish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shuo
 */
@SpringBootApplication
@MapperScan("com.djsenglish.dao")
public class DjsenglishApplication {

    public static void main(String[] args) {
        SpringApplication.run(DjsenglishApplication.class, args);
    }


}
