package com.test.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created on 13.02.2016.
 */
@EnableAutoConfiguration
@SpringBootApplication
public class AtmApplication extends SpringBootServletInitializer {
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AtmApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AtmApplication.class, args);

        logger.info("Application ATM stared.");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AtmApplication.class);
    }

}
