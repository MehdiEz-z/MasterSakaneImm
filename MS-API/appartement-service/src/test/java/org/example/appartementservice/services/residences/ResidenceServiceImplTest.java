package org.example.appartementservice.services.residences;
import org.example.appartementservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.appartementservice.models.entities.Residence;
import org.example.appartementservice.repositories.ResidenceRepository;
import org.example.appartementservice.services.inmplementations.ResidenceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Optional;
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
        String reference = residenceService.generateReferenceResidence();
        assertNotNull(reference);
        assertEquals(residence1.getReference(), reference);
    }
    @Test
    void testGenerateSecondResidenceReference(){
        Residence residence1 = createResidence();
        Mockito.when(residenceRepository.existsByReference(residence1.getReference())).thenReturn(true);
        String reference = residenceService.generateReferenceResidence();
        assertNotNull(reference);
        assertEquals("RES-2", reference);
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
    @Test
    void testGetResidenceByReferenceEmptyAndThrowException(){
        String nomResidence = "RES-1";
        Mockito.when(residenceRepository.findByNom(nomResidence)).thenReturn(Optional.empty());
        String exceptedMessage = "La Residence " + nomResidence + " n'existe pas";
        ResourcesNotFoundException exception = assertThrows(ResourcesNotFoundException.class,
                () -> residenceService.getResidenceByReference(nomResidence));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGetResidenceByReferenceSuccess(){
        Residence residence1 = createResidence();
        Mockito.when(residenceRepository.findByNom(residence1.getNom())).thenReturn(Optional.of(residence1));
        Residence residence = residenceService.getResidenceByReference(residence1.getNom());
        assertNotNull(residence);
        assertEquals(residence1.getReference(), residence.getReference());
    }
    @Test
    void testGetAllResidences(){
        Residence residence1 = createResidence();
        Residence residence2 = Residence.builder()
                .reference("RES-2")
                .nom("Residence 2")
                .description("Description 2")
                .adresse("Adresse 2")
                .build();
        Mockito.when(residenceRepository.findAll()).thenReturn(List.of(residence1, residence2));
        List<Residence> residences = residenceService.getAllResidences();
        assertNotNull(residences);
        assertEquals(2, residences.size());
    }
}