package org.example.clientservice.services.clients;
import org.example.clientservice.models.entities.Client;
import org.example.clientservice.models.enums.Methode;
import org.example.clientservice.models.enums.SituationFamiliale;
import org.example.clientservice.repositories.ClientRepository;
import org.example.clientservice.services.implementations.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import java.time.LocalDate;
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
                .reference("CLT-1")
                .nom("Nom-1")
                .prenom("Prenom-1")
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
}