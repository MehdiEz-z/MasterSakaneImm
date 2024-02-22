package org.example.appartementservice.services.magasins;
import org.example.appartementservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.appartementservice.models.entities.Etage;
import org.example.appartementservice.models.entities.Immeuble;
import org.example.appartementservice.models.entities.Magasin;
import org.example.appartementservice.models.enums.StatusAppEtMag;
import org.example.appartementservice.repositories.MagasinRepository;
import org.example.appartementservice.services.implementations.MagasinServiceImpl;
import org.example.appartementservice.services.interfaces.EtageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
class MagasinServiceImplTest {
    private MagasinServiceImpl magasinService;
    private MagasinRepository magasinRepository;
    private EtageService etageService;
    @BeforeEach
    void setUp() {
        magasinRepository = Mockito.mock(MagasinRepository.class);
        etageService = Mockito.mock(EtageService.class);
        magasinService = new MagasinServiceImpl(magasinRepository,etageService);
    }
    private Magasin createMagasin() {
        return Magasin.builder()
                .reference("RES-1-IMM-1-ETG-1-MAG-1")
                .numero("Magasin-1")
                .description("Magasin de type Boutique")
                .titreFoncier("Titre Foncier-1")
                .superficieUtile(100)
                .superficieMezaznine(20)
                .prixGlobalInitial(2200000.0)
                .prixMetreCarre(22000.0)
                .etage(Etage.builder()
                        .reference("RES-1-IMM-1-ETG-1")
                        .immeuble(Immeuble.builder().reference("RES-1-IMM-1").build())
                        .build())
                .status(StatusAppEtMag.DISPONIBLE)
                .build();
    }
    @Test
    void testGenerateReferenceMagasinWithNotExistingEtageAndThrowException(){
        Magasin magasin1 = createMagasin();
        Mockito.when(etageService.getEtageByReference(magasin1.getEtage().getReference()))
                .thenReturn(null);
        String exceptedMessage = "L'Etage \"" + magasin1.getEtage().getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> magasinService.generateReferenceMagasin(magasin1));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGenerateReferenceMagasin(){
        Magasin magasin1 = createMagasin();
        Mockito.when(etageService.getEtageByReference(magasin1.getEtage().getReference()))
                .thenReturn(magasin1.getEtage());
        String reference = magasinService.generateReferenceMagasin(magasin1);
        assertEquals(magasin1.getReference(), reference);
    }
    @Test
    void testGenerateSecondReferenceMagasin(){
        Magasin magasin1 = createMagasin();
        Mockito.when(etageService.getEtageByReference(magasin1.getEtage().getReference()))
                .thenReturn(magasin1.getEtage());
        Mockito.when(magasinRepository.existsByReference(magasin1.getReference()))
                .thenReturn(true);
        String reference = magasinService.generateReferenceMagasin(magasin1);
        assertNotNull(reference);
        assertEquals("RES-1-IMM-1-ETG-1-MAG-2", reference);
    }
    @Test
    void testCreateMagasinWithNotExistingEtageAndThrowException(){
        Magasin magasin1 = createMagasin();
        Mockito.when(etageService.getEtageByReference(magasin1.getEtage().getReference()))
                .thenReturn(null);
        String exceptedMessage = "L'Etage \"" + magasin1.getEtage().getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> magasinService.createMagasin(magasin1));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testCreateMagasinSuccess(){
        Magasin magasin1 = createMagasin();
        Magasin magasin2 = Magasin.builder()
                .numero("Magasin-1")
                .description("Magasin de type Boutique")
                .titreFoncier("Titre Foncier-1")
                .superficieUtile(100)
                .superficieMezaznine(20)
                .prixGlobalInitial(2200000.0)
                .prixMetreCarre(22000.0)
                .etage(Etage.builder()
                        .reference("RES-1-IMM-1-ETG-1")
                        .immeuble(Immeuble.builder().reference("RES-1-IMM-1").build())
                        .build())
                .status(StatusAppEtMag.DISPONIBLE)
                .build();
        Mockito.when(etageService.getEtageByReference(magasin1.getEtage().getReference()))
                .thenReturn(magasin1.getEtage());
        Mockito.when(magasinRepository.save(magasin2))
                .thenReturn(magasin2);
        Magasin createdMagasin = magasinService.createMagasin(magasin2);
        assertNotNull(createdMagasin);
        assertEquals(magasin1.getReference(), createdMagasin.getReference());
    }
    @Test
    void testGetMagasinByReferenceNotFoundAndThrowException(){
        Magasin magasin1 = createMagasin();
        String reference = magasin1.getReference();
        Mockito.when(magasinRepository.findByReference(reference))
                .thenReturn(null);
        String exceptedMessage = "Le Magasin \"" + reference + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> magasinService.getMagasinByReference(reference));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGetMagasinByReference(){
        Magasin magasin1 = createMagasin();
        Mockito.when(magasinRepository.findByReference(magasin1.getReference()))
                .thenReturn(magasin1);
        Magasin magasin = magasinService.getMagasinByReference(magasin1.getReference());
        assertNotNull(magasin);
        assertEquals(magasin1.getReference(), magasin.getReference());
    }
    @Test
    void testGetAllMagasinsByEtageSuccess(){
        Magasin magasin1 = createMagasin();
        Magasin magasin2 = Magasin.builder()
                .reference("RES-1-IMM-1-ETG-1-MAG-2")
                .numero("Magasin-2")
                .description("Magasin de type Boutique")
                .titreFoncier("Titre Foncier-2")
                .superficieUtile(100)
                .superficieMezaznine(20)
                .prixGlobalInitial(2200000.0)
                .prixMetreCarre(22000.0)
                .etage(Etage.builder()
                        .reference("RES-1-IMM-1-ETG-1")
                        .immeuble(Immeuble.builder().reference("RES-1-IMM-1").build())
                        .build())
                .status(StatusAppEtMag.DISPONIBLE)
                .build();
        Mockito.when(etageService.getEtageByReference("RES-1-IMM-1-ETG-1"))
                .thenReturn(magasin1.getEtage());
        Mockito.when(magasinRepository.findAllByEtage_Reference("RES-1-IMM-1-ETG-1"))
                .thenReturn(java.util.List.of(magasin1, magasin2));
        java.util.List<Magasin> magasins = magasinService.getAllMagasinByEtage("RES-1-IMM-1-ETG-1");
        assertNotNull(magasins);
        assertEquals(2, magasins.size());
    }
}