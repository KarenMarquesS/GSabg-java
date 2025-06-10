package org.example.gsabg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
@EntityScan
@EnableCaching
@SpringBootApplication
public class GSabgApplication {

    public static void main(String[] args) {
        SpringApplication.run(GSabgApplication.class, args);
    }

}
