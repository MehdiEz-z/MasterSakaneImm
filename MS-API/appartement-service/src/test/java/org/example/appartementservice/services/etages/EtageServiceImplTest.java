package org.example.appartementservice.services.etages;
import org.example.appartementservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.appartementservice.models.entities.Etage;
import org.example.appartementservice.models.entities.Immeuble;
import org.example.appartementservice.models.entities.Residence;
import org.example.appartementservice.repositories.EtageRepository;
import org.example.appartementservice.services.implementations.EtageServiceImpl;
import org.example.appartementservice.services.interfaces.ImmeubleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class EtageServiceImplTest {
    private EtageServiceImpl etageService;
    private EtageRepository etageRepository;
    private ImmeubleService immeubleService;
    @BeforeEach
    void setUp() {
        etageRepository = Mockito.mock(EtageRepository.class);
        immeubleService = Mockito.mock(ImmeubleService.class);
        etageService = new EtageServiceImpl(etageRepository, immeubleService);
    }
    private Etage createEtage() {
        return Etage.builder()
                .reference("RES-1-IMM-1-ETG-1")
                .numero("Etage-1")
                .immeuble(Immeuble.builder()
                        .reference("RES-1-IMM-1")
                        .residence(Residence.builder().reference("RES-1").build())
                        .build())
                .build();
    }
    @Test
    void testGenerateReferenceEtageWithNotExistingImmeubleAndThrowException(){
        Etage etage1 = createEtage();
        Mockito.when(immeubleService.getImmeubleByReference(etage1.getImmeuble().getReference()))
                .thenReturn(null);
        String exceptedMessage = "L'Immeuble \"" + etage1.getImmeuble().getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> etageService.generateReferenceEtage(etage1));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGenerateReferenceEtage(){
        Etage etage1 = createEtage();
        Mockito.when(immeubleService.getImmeubleByReference(etage1.getImmeuble().getReference()))
                .thenReturn(etage1.getImmeuble());
        String reference = etageService.generateReferenceEtage(etage1);
        assertNotNull(reference);
        assertEquals(etage1.getReference(), reference);
    }
    @Test
    void testGenerateSecondEtageReference(){
        Etage etage1 = createEtage();
        Mockito.when(immeubleService.getImmeubleByReference(etage1.getImmeuble().getReference()))
                .thenReturn(etage1.getImmeuble());
        Mockito.when(etageRepository.existsByReference(etage1.getReference())).thenReturn(true);
        String reference = etageService.generateReferenceEtage(etage1);
        assertNotNull(reference);
        assertEquals("RES-1-IMM-1-ETG-2", reference);
    }
    @Test
    void testCreateEtageNotExistingImmeuble(){
        Etage etage1 = createEtage();
        Mockito.when(immeubleService.getImmeubleByReference(etage1.getImmeuble().getReference()))
                .thenReturn(null);
        String exceptedMessage = "L'Immeuble \"" + etage1.getImmeuble().getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> etageService.createEtage(etage1));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testCreateEtageSuccess(){
        Etage etage1 = createEtage();
        Etage etage2 = Etage.builder()
                .numero("Etage-1")
                .immeuble(Immeuble.builder()
                        .reference("RES-1-IMM-1")
                        .residence(Residence.builder().reference("RES-1").build())
                        .build())
                .build();
        Mockito.when(immeubleService.getImmeubleByReference(etage1.getImmeuble().getReference()))
                .thenReturn(etage1.getImmeuble());
        Mockito.when(etageRepository.save(etage2)).thenReturn(etage2);
        Etage createdEtage = etageService.createEtage(etage2);
        assertNotNull(createdEtage);
        assertEquals(etage1.getReference(), createdEtage.getReference());
    }
    @Test
    void testGetEtageByReferenceNotFoundAndThrowException(){
        Etage etage1 = createEtage();
        Mockito.when(etageRepository.findByReference(etage1.getReference()))
                .thenReturn(null);
        String exceptedMessage = "L'Etage \"" + etage1.getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> etageService.getEtageByReference(etage1.getReference()));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGetEtageByReferenceSuccess(){
        Etage etage1 = createEtage();
        Mockito.when(etageRepository.findByReference(etage1.getReference()))
                .thenReturn(etage1);
        Etage etage = etageService.getEtageByReference(etage1.getReference());
        assertNotNull(etage);
        assertEquals(etage1.getReference(), etage.getReference());
    }
    @Test
    void testGetAllEtageByImmeubleSuccess(){
        Etage etage1 = createEtage();
        Etage etage2 = Etage.builder()
                .reference("RES-1-IMM-1-ETG-2")
                .numero("Etage-2")
                .immeuble(Immeuble.builder().reference("RES-1-IMM-1").build())
                .build();
        Etage etage3 = Etage.builder()
                .reference("RES-1-IMM-1-ETG-3")
                .numero("Etage-3")
                .immeuble(Immeuble.builder().reference("RES-1-IMM-1").build())
                .build();
        Etage etage4 = Etage.builder()
                .reference("RES-1-IMM-1-ETG-4")
                .numero("Etage-4")
                .immeuble(Immeuble.builder().reference("RES-1-IMM-1").build())
                .build();
        Mockito.when(immeubleService.getImmeubleByReference(etage1.getImmeuble().getReference()))
                .thenReturn(etage1.getImmeuble());
        Mockito.when(etageRepository.findAllByImmeuble_Reference("RES-1-IMM-1"))
                .thenReturn(List.of(etage1, etage2, etage3, etage4));
        List<Etage> etages = etageService.getAllEtageByImmeuble("RES-1-IMM-1");
        assertNotNull(etages);
        assertEquals(4, etages.size());
    }
}