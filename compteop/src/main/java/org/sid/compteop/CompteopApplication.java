package org.sid.compteop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CompteopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteopApplication.class, args);
    }

}
