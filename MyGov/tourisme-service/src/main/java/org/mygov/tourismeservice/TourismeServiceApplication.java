package org.mygov.tourismeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TourismeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TourismeServiceApplication.class, args);
    }

}
