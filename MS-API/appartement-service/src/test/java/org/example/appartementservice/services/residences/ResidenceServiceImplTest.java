package org.example.appartementservice.services.residences;
import org.example.appartementservice.handlers.exceptionHandler.OperationsException;
import org.example.appartementservice.models.entities.Residence;
import org.example.appartementservice.repositories.ResidenceRepository;
import org.example.appartementservice.services.inmplementations.ResidenceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
class ResidenceServiceImplTest {
    private ResidenceServiceImpl residenceService;
    private ResidenceRepository residenceRepository;
    @BeforeEach
    void setUp() {
        residenceRepository = Mockito.mock(ResidenceRepository.class);
        residenceService = new ResidenceServiceImpl(residenceRepository);
    }
    private Residence createResidence() {
        return Residence.builder()
                .reference("RES-1")
                .nom("Residence 1")
                .description("Description 1")
                .adresse("Adresse 1")
                .build();
    }
    @Test
    void testGenerateReferenceResidence(){
        Residence residence1 = createResidence();
        Residence residence2 = Residence.builder()
                .nom("Residence 1")
                .description("Description 1")
                .adresse("Adresse 1")
                .build();
        String reference = residenceService.generateReferenceResidence(residence2);
        assertNotNull(reference);
        assertEquals(residence1.getReference(), reference);
    }
    @Test
    void testGenerateSecondResidenceReference(){
        Residence residence2 = Residence.builder()
                .nom("Residence 2")
                .description("Description 2")
                .adresse("Adresse 2")
                .build();
        Mockito.when(residenceRepository.count()).thenReturn(1L);
        String reference = residenceService.generateReferenceResidence(residence2);
        assertNotNull(reference);
        assertEquals("RES-2", reference);
    }
    @Test
    void testGenerateReferenceResidenceFailAndThrowException(){
        Residence residence2 = Residence.builder()
                .nom("Residence 2")
                .description("Description 2")
                .adresse("Adresse 2")
                .build();
        Mockito.when(residenceRepository.count()).thenReturn((long) -1);
        OperationsException exception = assertThrows(OperationsException.class,
                () -> residenceService.generateReferenceResidence(residence2));
        String expectedMessage = "Erreur lors de la génération de la référence de la résidence";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void testCreateResidenceSuccess(){
        Residence residence1 = createResidence();
        Residence residence2 = Residence.builder()
                .nom("Residence 1")
                .description("Description 1")
                .adresse("Adresse 1")
                .build();
        Mockito.when(residenceRepository.save(residence2)).thenReturn(residence2);
        Residence createdResidence = residenceService.createResidence(residence2);
        assertNotNull(createdResidence);
        assertEquals(residence1.getReference(), residence2.getReference());
    }
}