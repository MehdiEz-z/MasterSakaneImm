package org.example.clientservice.services.clients;
import org.example.clientservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.clientservice.models.entities.Client;
import org.example.clientservice.models.enums.Methode;
import org.example.clientservice.models.enums.SituationFamiliale;
import org.example.clientservice.repositories.ClientRepository;
import org.example.clientservice.services.implementations.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
class ClientServiceImplTest {
    private ClientServiceImpl clientService;
    private ClientRepository clientRepository;
    @BeforeEach
    void setUp() {
        clientRepository = Mockito.mock(ClientRepository.class);
        clientService = new ClientServiceImpl(clientRepository);
    }
    private Client createClient() {
        return Client.builder()
                .reference("ME-EZZ-90-1")
                .nom("Mehdi")
                .prenom("Ezzahri")
                .dateNaissance(LocalDate.of(1990, 1, 1))
                .lieuNaissance("Lieu-1")
                .nomPere("Nom-Pere-1")
                .nomMere("Nom-Mere-1")
                .adresse("Adresse-1")
                .cin("BJ123456")
                .cinValidite(LocalDate.of(2025, 1, 1))
                .telephone("+212600000001")
                .nationalite("Marocain")
                .email("mehdi@gmail.com")
                .profession("Profession-1")
                .situationFamiliale(SituationFamiliale.CELIBATAIRE)
                .methode(Methode.FACEBOOK)
                .build();
    }
    @Test
    void testGenerateReferenceClient(){
        Client client1 = createClient();
        Client client2 = Client.builder()
                .nom("Mehdi")
                .prenom("Ezzahri")
                .dateNaissance(LocalDate.of(1990, 1, 1))
                .build();
        String reference = clientService.generateReferenceClient(client2);
        assertNotNull(reference);
        assertEquals(client1.getReference(), reference);
    }
    @Test
    void testGenerateClientReferenceWithSameCode(){
        Client client1 = createClient();
        Client client2 = Client.builder()
                .nom("Mehdi")
                .prenom("Ezzahri")
                .dateNaissance(LocalDate.of(1990, 1, 1))
                .build();
        Mockito.when(clientRepository.existsByReference(client1.getReference()))
                .thenReturn(true);
        String reference = clientService.generateReferenceClient(client2);
        assertNotNull(reference);
        assertEquals("ME-EZZ-90-2", reference);
    }
    @Test
    void testCreateClientEmailAlreadyExistAndThrowException(){
        Client client = createClient();
        Mockito.when(clientRepository.existsByEmail(client.getEmail()))
                .thenReturn(true);
        String exceptedMessage = "L'email \"" + client.getEmail() + "\" appartient déjà à un autre client";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> clientService.createClient(client));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testCreateClientCinAlreadyExistAndThrowException(){
        Client client = createClient();
        Mockito.when(clientRepository.existsByCin(client.getCin()))
                .thenReturn(true);
        String exceptedMessage = "La CIN \"" + client.getCin() + "\" appartient déjà à un autre client";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> clientService.createClient(client));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testCreateClientSuccess(){
        Client client = createClient();
        Client client2 = Client.builder()
                .nom("Mehdi")
                .prenom("Ezzahri")
                .dateNaissance(LocalDate.of(1990, 1, 1))
                .email("mehdi@gmail.com")
                .cin("BJ123456")
                .build();
        Mockito.when(clientRepository.existsByEmail(client.getEmail()))
                .thenReturn(false);
        Mockito.when(clientRepository.existsByCin(client.getCin()))
                .thenReturn(false);
        Mockito.when(clientRepository.save(client2))
                .thenReturn(client2);
        Client savedClient = clientService.createClient(client2);
        assertNotNull(savedClient);
        assertEquals(client.getReference(), savedClient.getReference());
    }
    @Test
    void testGetClientByReferenceEmptyAndThrowException(){
        String referenceClient = "ME-EZZ-90-1";
        Mockito.when(clientRepository.findByReference(referenceClient))
                .thenReturn(Optional.empty());
        String exceptedMessage = "Le client \"" + referenceClient + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> clientService.getClientByReference(referenceClient));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGetClientByReferenceSuccess(){
        Client client = createClient();
        Mockito.when(clientRepository.findByReference(client.getReference()))
                .thenReturn(Optional.of(client));
        Client client1 = clientService.getClientByReference(client.getReference());
        assertNotNull(client1);
        assertEquals(client.getReference(),client1.getReference());
    }
    @Test
    void testGetClientByCinEmptyAndThrowException(){
        String cinClient = "BJ123456";
        Mockito.when(clientRepository.findByCin(cinClient))
                .thenReturn(Optional.empty());
        String exceptedMessage = "Le client avec la cin :  \"" + cinClient + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> clientService.getClientByCin(cinClient));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGetClientByCinSuccess(){
        Client client = createClient();
        Mockito.when(clientRepository.findByCin(client.getCin()))
                .thenReturn(Optional.of(client));
        Client client1 = clientService.getClientByCin(client.getCin());
        assertNotNull(client1);
        assertEquals(client,client1);
    }
    @Test
    void testGetAllClient(){
        Client client = createClient();
        Client client1 = Client.builder()
                .reference("AN-CHO-94-1")
                .nom("Anass")
                .prenom("Choubri")
                .cin("BQ654321")
                .dateNaissance(LocalDate.of(1994, 1, 1))
                .email("anass@gmail.com")
                .build();
        Client client2 = Client.builder()
                .reference("AN-CHO-94-2")
                .nom("Anass")
                .prenom("Choubri")
                .cin("BQ6549871")
                .dateNaissance(LocalDate.of(1994, 1, 1))
                .email("anass2@gmail.com")
                .build();
        Mockito.when(clientRepository.findAll()).thenReturn(List.of(client,client2,client1));
        List<Client> clients = clientService.getAllClients();
        assertNotNull(clients);
        assertEquals(3,clients.size());
    }
}