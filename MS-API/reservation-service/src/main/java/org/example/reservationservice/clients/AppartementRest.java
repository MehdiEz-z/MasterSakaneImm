package org.example.reservationservice.clients;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.reservationservice.models.model.Appartement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
@FeignClient(name = "APPARTEMENT-SERVICE")
public interface AppartementRest {
    @GetMapping("/appartements/{reference}")
    Appartement getAppartementByReference(@PathVariable String reference);
    @GetMapping("/appartements/all")
    List<Appartement> getAllAppartements();
}
