package org.example.clientservice.services.interfaces;
import org.example.clientservice.models.entities.Client;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface ClientService {
    Client createClient(Client client);
    Client getClientByReference(String clientReference);
    Client getClientByCin(String clientCin);
    List<Client> getAllClients();
}
