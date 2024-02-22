package org.example.appartementservice.services.immeubles;
import org.example.appartementservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.appartementservice.models.entities.Immeuble;
import org.example.appartementservice.models.entities.Residence;
import org.example.appartementservice.repositories.ImmeubleRepository;
import org.example.appartementservice.services.implementations.ImmeubleServiceImpl;
import org.example.appartementservice.services.interfaces.ResidenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class ImmeubleServiceImplTest {
    private ImmeubleServiceImpl immeubleService;
    private ImmeubleRepository immeubleRepository;
    private ResidenceService residenceService;
    @BeforeEach
    void setUp() {
        immeubleRepository = Mockito.mock(ImmeubleRepository.class);
        residenceService = Mockito.mock(ResidenceService.class);
        immeubleService = new ImmeubleServiceImpl(immeubleRepository, residenceService);
    }
    private Immeuble createImmeuble() {
        return Immeuble.builder()
                .reference("RES-1-IMM-1")
                .numero("Immeuble-1")
                .residence(Residence.builder().reference("RES-1").build())
                .build();
    }
    @Test
    void testGenerateReferenceImmeubleWithNotExistingResidenceAndThrowException(){
        Immeuble immeuble1 = createImmeuble();
        Mockito.when(residenceService.getResidenceByReference(immeuble1.getResidence().getReference()))
                .thenReturn(null);
        String exceptedMessage = "La Residence \"" + immeuble1.getResidence().getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> immeubleService.generateReferenceImmeuble(immeuble1));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGenerateReferenceImmeuble(){
        Immeuble immeuble1 = createImmeuble();
        Mockito.when(residenceService.getResidenceByReference(immeuble1.getResidence().getReference()))
                .thenReturn(immeuble1.getResidence());
        String reference = immeubleService.generateReferenceImmeuble(immeuble1);
        assertNotNull(reference);
        assertEquals(immeuble1.getReference(), reference);
    }
    @Test
    void testGenerateSecondImmeubleReference(){
        Immeuble immeuble1 = createImmeuble();
        Mockito.when(residenceService.getResidenceByReference(immeuble1.getResidence().getReference()))
                .thenReturn(immeuble1.getResidence());
        Mockito.when(immeubleRepository.existsByReference(immeuble1.getReference())).thenReturn(true);
        String reference = immeubleService.generateReferenceImmeuble(immeuble1);
        assertNotNull(reference);
        assertEquals("RES-1-IMM-2", reference);
    }
    @Test
    void testCreateImmeubleNotExistingResidence(){
        Immeuble immeuble2 = Immeuble.builder()
                .residence(Residence.builder().reference("RES-1").build())
                .build();
        Mockito.when(residenceService.getResidenceByReference(immeuble2.getResidence().getReference()))
                .thenReturn(null);
        String exceptedMessage = "La Residence \"" + immeuble2.getResidence().getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> immeubleService.createImmeuble(immeuble2));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testCreateImmeubleSuccess(){
        Immeuble immeuble1 = createImmeuble();
        Immeuble immeuble2 = Immeuble.builder()
                .numero("Immeuble-1")
                .residence(Residence.builder().reference("RES-1").build())
                .build();
        Mockito.when(residenceService.getResidenceByReference(immeuble2.getResidence().getReference()))
                .thenReturn(immeuble2.getResidence());
        Mockito.when(immeubleRepository.save(immeuble2)).thenReturn(immeuble2);
        Immeuble createdImmeuble = immeubleService.createImmeuble(immeuble2);
        assertNotNull(createdImmeuble);
        assertEquals(immeuble1.getReference(), immeuble2.getReference());
    }
    @Test
    void testGetImmeubleByReferenceNotFoundAndThrowException(){
        Immeuble immeuble1 = createImmeuble();
        String reference = immeuble1.getReference();
        Mockito.when(immeubleRepository.findByReference(immeuble1.getReference()))
                .thenReturn(null);
        String exceptedMessage = "L'Immeuble \"" + immeuble1.getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> immeubleService.getImmeubleByReference(reference));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGetImmeubleByReferenceSuccess(){
        Immeuble immeuble1 = createImmeuble();
        Mockito.when(immeubleRepository.findByReference(immeuble1.getReference()))
                .thenReturn(immeuble1);
        Immeuble immeuble = immeubleService.getImmeubleByReference(immeuble1.getReference());
        assertNotNull(immeuble);
        assertEquals(immeuble1.getReference(), immeuble.getReference());
    }
    @Test
    void testGetAllImmeubleByResidence(){
        Immeuble immeuble1 = createImmeuble();
        Immeuble immeuble2 = Immeuble.builder()
                .reference("RES-1-IMM-2")
                .numero("Immeuble-2")
                .residence(Residence.builder().reference("RES-1").build())
                .build();
        Immeuble immeuble3 = Immeuble.builder()
                .reference("RES-1-IMM-3")
                .numero("Immeuble-3")
                .residence(Residence.builder().reference("RES-1").build())
                .build();
        Mockito.when(residenceService.getResidenceByReference("RES-1"))
                .thenReturn(Residence.builder().reference("RES-1").build());
        Mockito.when(immeubleRepository.findAllByResidence_Reference("RES-1"))
                .thenReturn(List.of(immeuble1, immeuble2, immeuble3));
        List<Immeuble> immeubles = immeubleService.getAllImmeubleByResidence("RES-1");
        assertNotNull(immeubles);
        assertEquals(3, immeubles.size());
    }
}
