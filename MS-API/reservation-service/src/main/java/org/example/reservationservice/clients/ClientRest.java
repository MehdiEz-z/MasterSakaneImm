package org.example.reservationservice.clients;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.reservationservice.models.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
@FeignClient(name = "CLIENT-SERVICE")
@CircuitBreaker(name = "client-service", fallbackMethod = "getDefaultClient")
public interface ClientRest {
    @GetMapping("/clients/{reference}")
    Client getClientByReference(@PathVariable String reference);
    @GetMapping("/clients/all")
    List<Client> getAllClients();
    default Client fallback(String reference, Throwable e) {
        return Client.builder()
                .nom("Nom par défaut")
                .prenom("Prenom par défaut")
                .build();
    }
}
