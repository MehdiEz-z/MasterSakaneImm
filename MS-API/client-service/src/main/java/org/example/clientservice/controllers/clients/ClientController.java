package org.example.clientservice.controllers.clients;
import jakarta.validation.Valid;
import org.example.clientservice.controllers.clients.vms.request.ClientRequestVM;
import org.example.clientservice.controllers.clients.vms.response.ClientResponseVM;
import org.example.clientservice.handlers.response.ResponseMessage;
import org.example.clientservice.models.entities.Client;
import org.example.clientservice.services.interfaces.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/{cin}")
    public ResponseEntity<ResponseMessage> getClientByCin(@PathVariable String cin) {
        Client client = clientService.getClientByCin(cin);
        return ResponseMessage.ok(ClientResponseVM.toVM(client),
                "Client trouvé avec succès");
    }
    @GetMapping("/all")
    public ResponseEntity<ResponseMessage> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        if(clients.isEmpty()) {
            return ResponseMessage.notFound("Aucun Client trouvé");
        }else {
            return ResponseMessage.ok(clients.stream()
                            .map(ClientResponseVM::toVM).toList(),
                    "Clients trouvés avec succès");
        }
    }
    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createClient(@RequestBody @Valid ClientRequestVM clientRequestVM){
        Client client = clientRequestVM.toClient();
        Client createdClient = clientService.createClient(client);
        return ResponseMessage.created(ClientResponseVM.toVM(createdClient),
                "Client Crée avec succès");
    }
}
