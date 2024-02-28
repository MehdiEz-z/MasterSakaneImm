package org.example.reservationservice.clients;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.reservationservice.models.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "CLIENT-SERVICE")
public interface ClientRest {
    @GetMapping("/clients/{reference}")
    @CircuitBreaker(name = "client-service", fallbackMethod = "getDefaultClient")
    Client getClientByReference(@PathVariable String reference);
    default Client getDefaultClient(String reference, Throwable e) {
        return Client.builder()
                .nom("Nom par défaut")
                .prenom("Prenom par défaut")
                .build();
    }
}
