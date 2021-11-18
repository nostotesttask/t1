package org.example.currencyRater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyRater {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyRater.class, args);
    }
}
