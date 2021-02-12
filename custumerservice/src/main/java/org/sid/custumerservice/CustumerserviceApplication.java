package org.sid.custumerservice;

import org.sid.custumerservice.entities.Client;
import org.sid.custumerservice.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustumerserviceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CustumerserviceApplication.class, args);
    }


    CommandLineRunner start(ClientRepository clientRepository, RepositoryRestConfiguration restConfiguration) {
        restConfiguration.exposeIdsFor(Client.class);
        return args -> {
            clientRepository.save(new Client(null, "Hajar", "Hajar@gmail.com"));
            clientRepository.save(new Client(null, "Ouiam", "Ouiam@gmail.com"));
            clientRepository.save(new Client(null, "Oumaima", "Oumaima@gmail.com"));
            clientRepository.findAll().forEach(customer -> {
                System.out.println(customer.toString());
            });

        };
    }
}


