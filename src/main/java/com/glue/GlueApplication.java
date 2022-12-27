package com.glue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan(basePackages = {"com.glue.entities"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GlueApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlueApplication.class, args);
    }

}
