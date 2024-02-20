package org.example.appartementservice.services.inmplementations;
import org.example.appartementservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.appartementservice.models.entities.Residence;
import org.example.appartementservice.repositories.ResidenceRepository;
import org.example.appartementservice.services.interfaces.ResidenceService;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class ResidenceServiceImpl implements ResidenceService{
    private final ResidenceRepository residenceRepository;
    public ResidenceServiceImpl(ResidenceRepository residenceRepository) {
        this.residenceRepository = residenceRepository;
    }
    @Override
    public Residence createResidence(Residence residence) {
        String reference = generateReferenceResidence();
        residence.setReference(reference);
        return residenceRepository.save(residence);
    }
    public String generateReferenceResidence() {
        int suffix = 1;
        String reference = "RES-" + suffix;
        while (residenceRepository.existsByReference(reference)) {
            suffix++;
            reference = "RES-" + suffix;
        }
        return reference;
    }
    @Override
    public Residence getResidenceByReference(String nomResidence) {
        return residenceRepository.findByNom(nomResidence)
                .orElseThrow(() -> new ResourcesNotFoundException("La Residence " + nomResidence + " n'existe pas"));
    }
    @Override
    public List<Residence> getAllResidences() {
        return residenceRepository.findAll();
    }
}
