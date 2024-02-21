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
                () -> immeubleService.generateReferenceImmeuble());
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGenerateReferenceImmeuble(){
        Immeuble immeuble1 = createImmeuble();
        String reference = immeubleService.generateReferenceImmeuble();
        assertNotNull(reference);
        assertEquals(immeuble1.getReference(), reference);
    }
    @Test
    void testGenerateSecondImmeubleReference(){
        Immeuble immeuble1 = createImmeuble();
        Mockito.when(immeubleRepository.existsByReference(immeuble1.getReference())).thenReturn(true);
        String reference = immeubleService.generateReferenceImmeuble();
        assertNotNull(reference);
        assertEquals("RES-1-IMM-2", reference);
    }
    @Test
    void testCreateImmeubleSuccess(){
        Immeuble immeuble1 = createImmeuble();
        Immeuble immeuble2 = Immeuble.builder()
                .numero("Immeuble-1")
                .residence(Residence.builder().reference("RES-1").build())
                .build();
        Mockito.when(immeubleRepository.save(immeuble2)).thenReturn(immeuble2);
        Immeuble createdImmeuble = immeubleService.createImmeuble(immeuble2);
        assertNotNull(createdImmeuble);
        assertEquals(immeuble1.getReference(), immeuble2.getReference());
    }
    @Test
    void testGetImmeubleByReferenceAndResidenceNotFoundAndThrowException(){
        Immeuble immeuble1 = createImmeuble();
        String immeubleReference = immeuble1.getReference();
        String residenceReference = immeuble1.getResidence().getReference();
        Mockito.when(residenceService.getResidenceByReference(residenceReference))
                .thenReturn(null);
        String exceptedMessage = "La Residence \"" + immeuble1.getResidence().getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> immeubleService.getImmeubleByReferenceAndResidence(immeubleReference, residenceReference));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGetImmeubleByReferenceNotFoundAndThrowException(){
        Immeuble immeuble1 = createImmeuble();
        String immeubleReference = immeuble1.getReference();
        String residenceReference = immeuble1.getResidence().getReference();
        Mockito.when(residenceService.getResidenceByReference(residenceReference))
                .thenReturn(Residence.builder().reference(residenceReference).build());
        Mockito.when(immeubleRepository.findByReferenceAndResidence_Reference(immeubleReference, residenceReference))
                .thenReturn(null);
        String exceptedMessage = "L'Immeuble \"" + immeuble1.getReference() + "\" n'existe pas";
        Exception exception = assertThrows(ResourcesNotFoundException.class,
                () -> immeubleService.getImmeubleByReferenceAndResidence(immeubleReference, residenceReference));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testGetImmeubleByReferenceAndResidenceSuccess(){
        Immeuble immeuble1 = createImmeuble();
        Mockito.when(residenceService.getResidenceByReference(immeuble1.getResidence().getReference()))
                .thenReturn(Residence.builder().reference(immeuble1.getResidence().getReference()).build());
        Mockito.when(immeubleRepository.findByReferenceAndResidence_Reference(immeuble1.getReference(), immeuble1.getResidence().getReference()))
                .thenReturn(immeuble1);
        Immeuble immeuble = immeubleService.getImmeubleByReferenceAndResidence(immeuble1.getReference(), immeuble1.getResidence().getReference());
        assertNotNull(immeuble);
        assertEquals(immeuble1.getReference(), immeuble.getReference());
    }
}
