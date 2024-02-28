package org.example.reservationservice.clients;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.reservationservice.models.model.Appartement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
@FeignClient(name = "APPARTEMENT-SERVICE")
public interface AppartementRest {
    @GetMapping("/appartements/{reference}")
    @CircuitBreaker(name = "appartement-service", fallbackMethod = "getDefaultAppartement")
    Appartement getAppartementByReference(@PathVariable String reference);
    @PutMapping("/appartements/{reference}/status/{newStatus}")
    void updateAppartementStatus(@PathVariable String reference, @PathVariable String newStatus);

    default Appartement getDefaultAppartement(String reference, Throwable e) {
        return Appartement.builder()
                .residence("Résidence par défaut")
                .immeuble("Immeuble par défaut")
                .etage("Etage par défaut")
                .numero("Numéro par défaut")
                .status("Status par défaut")
                .superficieUtile(0)
                .superficieTerrasse(0)
                .prixMetreCarre(0)
                .build();
    }
}
