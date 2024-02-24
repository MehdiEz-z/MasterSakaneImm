package org.example.clientservice.services.implementations;
import org.example.clientservice.models.entities.Client;
import org.example.clientservice.repositories.ClientRepository;
import org.example.clientservice.services.interfaces.ClientService;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public Client createClient(Client client) {
        return null;
    }
    @Override
    public Client getClientByReference(String clientReference) {
        return null;
    }
    @Override
    public Client getClientByCin(String clientCin) {
        return null;
    }
    @Override
    public List<Client> getAllClients() {
        return null;
    }
    public String generateReferenceClient(Client client) {
        String deuxPremiersCaracteresNom = client.getNom().substring(0,  Math.min(client.getNom().length(), 2)).toUpperCase();
        String troisPremiersCaracteresPrenom = client.getPrenom().substring(0,  Math.min(client.getPrenom().length(), 3)).toUpperCase();
        String anneeNaissance = String.valueOf(client.getDateNaissance().getYear()).substring(2);
        int suffix = 1;
        return deuxPremiersCaracteresNom + "-" + troisPremiersCaracteresPrenom + "-" + anneeNaissance + "-" + suffix;
    }
}
