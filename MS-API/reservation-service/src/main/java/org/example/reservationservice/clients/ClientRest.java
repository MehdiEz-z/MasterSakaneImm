package org.example.reservationservice.clients;
import org.example.reservationservice.models.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
@FeignClient(name = "CLIENT-SERVICE")
public interface ClientRest {
    @GetMapping("/clients/{reference}")
    Client getClientByReference(@PathVariable String reference);
    @GetMapping("/clients/all")
    List<Client> getAllClients();
}
