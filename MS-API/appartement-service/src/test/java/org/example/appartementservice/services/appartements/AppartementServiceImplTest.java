package org.example.appartementservice.services.appartements;
import org.example.appartementservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.appartementservice.models.entities.Appartement;
import org.example.appartementservice.models.entities.Etage;
import org.example.appartementservice.models.entities.Immeuble;
import org.example.appartementservice.models.enums.StatusAppEtMag;
import org.example.appartementservice.repositories.AppartementRepository;
import org.example.appartementservice.services.implementations.AppartementServiceImpl;
import org.example.appartementservice.services.interfaces.EtageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class AppartementServiceImplTest {
    private AppartementServiceImpl appartementService;
    private AppartementRepository appartementRepository;
    private EtageService etageService;
    @BeforeEach
    void setUp() {
        appartementRepository = Mockito.mock(AppartementRepository.class);
        etageService = Mockito.mock(EtageService.class);
        appartementService = new AppartementServiceImpl(appartementRepository,etageService);
    }
    private Appartement createAppartement() {
        return Appartement.builder()
                .reference("APP-1-IMM-1-ETG-1-APT-1")
                .numero("Appartement-1")
                .description("Appartement de type F3")
                .titreFoncier("Titre Foncier-1")
                .superficieUtile(100)
                .superficieTerrasse(20)
                .prixGlobalInitial(1200000.0)
                .prixMetreCarre(12000.0)
                .nombreChambre(2)
                .nombreSalon(1)
                .nombreCuisine(1)
                .nombreSalleDeBain(2)
                .nombrePlacard(3)
                .nombreBalcon(1)
                .etage(Etage.builder()
                        .reference("RES-1-IMM-1-ETG-1")
                        .immeuble(Immeuble.builder().reference("RES-1-IMM-1").build())
                        .build())
                .status(StatusAppEtMag.DISPONIBLE)
                .build();
    }
    @Test
    void testGenerateReferenceAppartementWithNotExistingEtageAndThrowException(){
        Appartement appartement1 = createAppartement();
        Mockito.when(etageService.getEtageByReference(appartement1.getEtage().getReference()))
                .thenReturn(null);
        String exceptedMessage = "L'Etage \"" + appartement1.getEtage().getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> appartementService.generateReferenceAppartement(appartement1));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGenerateReferenceAppartement(){
        Appartement appartement1 = createAppartement();
        Mockito.when(etageService.getEtageByReference(appartement1.getEtage().getReference()))
                .thenReturn(appartement1.getEtage());
        String reference = appartementService.generateReferenceAppartement(appartement1);
        assertNotNull(reference);
        assertEquals(appartement1.getReference(), reference);
    }
    @Test
    void testGenerateSecondAppartementReference(){
        Appartement appartement1 = createAppartement();
        Mockito.when(etageService.getEtageByReference(appartement1.getEtage().getReference()))
                .thenReturn(appartement1.getEtage());
        Mockito.when(appartementRepository.existsByReference(appartement1.getReference())).thenReturn(true);
        String reference = appartementService.generateReferenceAppartement(appartement1);
        assertNotNull(reference);
        assertEquals("APP-1-IMM-1-ETG-1-APT-2", reference);
    }
    @Test
    void testCreateAppartementNotExistingEtage(){
        Appartement appartement1 = createAppartement();
        Mockito.when(etageService.getEtageByReference(appartement1.getEtage().getReference()))
                .thenReturn(null);
        String exceptedMessage = "L'Etage \"" + appartement1.getEtage().getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> appartementService.createAppartement(appartement1));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testCreateAppartementSuccess(){
        Appartement appartement1 = createAppartement();
        Appartement appartement2 = Appartement.builder()
                .numero("Appartement-1")
                .description("Appartement de type F3")
                .titreFoncier("Titre Foncier-1")
                .superficieUtile(100)
                .superficieTerrasse(20)
                .prixGlobalInitial(1200000.0)
                .prixMetreCarre(12000.0)
                .nombreChambre(2)
                .nombreSalon(1)
                .nombreCuisine(1)
                .nombreSalleDeBain(2)
                .nombrePlacard(3)
                .nombreBalcon(1)
                .etage(Etage.builder()
                        .reference("RES-1-IMM-1-ETG-1")
                        .immeuble(Immeuble.builder().reference("RES-1-IMM-1").build())
                        .build())
                .status(StatusAppEtMag.DISPONIBLE)
                .build();
        Mockito.when(etageService.getEtageByReference(appartement1.getEtage().getReference()))
                .thenReturn(appartement1.getEtage());
        Mockito.when(appartementRepository.save(appartement2)).thenReturn(appartement2);
        Appartement createdAppartement = appartementService.createAppartement(appartement2);
        assertNotNull(createdAppartement);
        assertEquals(appartement1.getReference(), createdAppartement.getReference());
    }
    @Test
    void testGetAppartementByReferenceNotFoundAndThrowException(){
        Appartement appartement1 = createAppartement();
        String reference = appartement1.getReference();
        Mockito.when(appartementRepository.findByReference(appartement1.getReference()))
                .thenReturn(null);
        String exceptedMessage = "L'Appartement \"" + appartement1.getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> appartementService.getAppartementByReference(reference));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGetAppartementByReferenceSuccess(){
        Appartement appartement1 = createAppartement();
        Mockito.when(appartementRepository.findByReference(appartement1.getReference()))
                .thenReturn(appartement1);
        Appartement appartement = appartementService.getAppartementByReference(appartement1.getReference());
        assertNotNull(appartement);
        assertEquals(appartement1.getReference(), appartement.getReference());
    }
    @Test
    void testGetAllAppartementByEtageSuccess(){
        Appartement appartement1 = createAppartement();
        Appartement appartement2 = Appartement.builder()
                .reference("APP-1-IMM-1-ETG-1-APT-2")
                .numero("Appartement-2")
                .description("Appartement de type F3")
                .titreFoncier("Titre Foncier-2")
                .superficieUtile(100)
                .superficieTerrasse(20)
                .prixGlobalInitial(1200000.0)
                .prixMetreCarre(12000.0)
                .nombreChambre(2)
                .nombreSalon(1)
                .nombreCuisine(1)
                .nombreSalleDeBain(2)
                .nombrePlacard(3)
                .nombreBalcon(1)
                .etage(Etage.builder()
                        .reference("RES-1-IMM-1-ETG-1")
                        .immeuble(Immeuble.builder().reference("RES-1-IMM-1").build())
                        .build())
                .status(StatusAppEtMag.DISPONIBLE)
                .build();
        Appartement appartement3 = Appartement.builder()
                .reference("APP-1-IMM-1-ETG-1-APT-3")
                .numero("Appartement-3")
                .description("Appartement de type F3")
                .titreFoncier("Titre Foncier-3")
                .superficieUtile(100)
                .superficieTerrasse(20)
                .prixGlobalInitial(1200000.0)
                .prixMetreCarre(12000.0)
                .nombreChambre(2)
                .nombreSalon(1)
                .nombreCuisine(1)
                .nombreSalleDeBain(2)
                .nombrePlacard(3)
                .nombreBalcon(1)
                .etage(Etage.builder()
                        .reference("RES-1-IMM-1-ETG-1")
                        .immeuble(Immeuble.builder().reference("RES-1-IMM-1").build())
                        .build())
                .status(StatusAppEtMag.DISPONIBLE)
                .build();
        Mockito.when(appartementRepository.findAllByEtage_Reference(appartement1.getEtage().getReference()))
                .thenReturn(List.of(appartement1, appartement2));
        List<Appartement> appartements = appartementService.getAllAppartementByEtage(appartement1.getEtage().getReference());
        assertNotNull(appartements);
        assertEquals(3, appartements.size());
    }
}