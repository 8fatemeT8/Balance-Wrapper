package com.refah.walletwrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.refah.walletwrapper.repository")
public class WalletWrapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletWrapperApplication.class, args);
    }
}
