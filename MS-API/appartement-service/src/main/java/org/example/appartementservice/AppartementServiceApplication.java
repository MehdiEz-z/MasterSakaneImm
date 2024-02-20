package org.example.appartementservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@SpringBootApplication
@EnableJpaAuditing
public class AppartementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppartementServiceApplication.class, args);
    }

}
