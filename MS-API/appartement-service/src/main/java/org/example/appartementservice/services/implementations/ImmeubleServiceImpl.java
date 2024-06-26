package org.example.appartementservice.services.implementations;
import org.example.appartementservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.appartementservice.models.entities.Immeuble;
import org.example.appartementservice.repositories.ImmeubleRepository;
import org.example.appartementservice.services.interfaces.ImmeubleService;
import org.example.appartementservice.services.interfaces.ResidenceService;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class ImmeubleServiceImpl implements ImmeubleService {
    private final ImmeubleRepository immeubleRepository;
    private final ResidenceService residenceService;
    public ImmeubleServiceImpl(ImmeubleRepository immeubleRepository, ResidenceService residenceService) {
        this.immeubleRepository = immeubleRepository;
        this.residenceService = residenceService;
    }
    @Override
    public Immeuble createImmeuble(Immeuble immeuble) {
        checkResidenceExists(immeuble.getResidence().getReference());
        String reference = generateReferenceImmeuble(immeuble);
        immeuble.setReference(reference);
        return immeubleRepository.save(immeuble);
    }
    @Override
    public Immeuble getImmeubleByReference(String immeubleReference) {
        Immeuble foundImmeuble = immeubleRepository.findByReference(immeubleReference);
        if(foundImmeuble == null){
            throw new ResourcesNotFoundException("L'Immeuble \"" + immeubleReference + "\" n'existe pas");
        }
        return foundImmeuble;
    }
    @Override
    public List<Immeuble> getAllImmeubleByResidence(String residenceReference) {
        checkResidenceExists(residenceReference);
        return immeubleRepository.findAllByResidence_Reference(residenceReference);
    }
    public String generateReferenceImmeuble(Immeuble immeuble) {
        String residenceReference = immeuble.getResidence().getReference();
        checkResidenceExists(residenceReference);
        int suffix = 1;
        String reference = residenceReference + "-IMM-" + suffix;
        while (immeubleRepository.existsByReference(reference)) {
            suffix++;
            reference = residenceReference + "-IMM-" + suffix;
        }
        return reference;
    }
    private void checkResidenceExists(String residenceReference) {
        if (residenceService.getResidenceByReference(residenceReference) == null) {
            throw new ResourcesNotFoundException("La Residence \"" + residenceReference + "\" n'existe pas");
        }
    }
}
