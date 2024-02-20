package org.example.appartementservice.services.inmplementations;
import org.example.appartementservice.models.entities.Residence;
import org.example.appartementservice.repositories.ResidenceRepository;
import org.example.appartementservice.services.interfaces.ResidenceService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
@Component
public class ResidenceServiceImpl implements ResidenceService{
    private final ResidenceRepository residenceRepository;
    public ResidenceServiceImpl(ResidenceRepository residenceRepository) {
        this.residenceRepository = residenceRepository;
    }
    @Override
    public Residence createResidence(Residence residence) {
        return null;
    }
    public String generateReferenceResidence(Residence residence) {
        long count = residenceRepository.count();
        String reference = "RES-" + (count + 1);
        if (residence.getReference() == null || residence.getReference().isEmpty()) {
            residence.setReference(reference);
        }
        return reference;
    }
    @Override
    public Residence getResidenceByReference(String reference) {
        return null;
    }
    @Override
    public List<Residence> getAllResidences() {
        return Collections.emptyList();
    }
}
