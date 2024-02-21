package org.example.appartementservice.services.implementations;
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
    public Immeuble createImmeuble(Immeuble residence) {
        return null;
    }
    public String generateReferenceImmeuble() {
        return null;
    }
    @Override
    public Immeuble getImmeubleByReferenceAndResidence(String immeubleReference, String residenceReference) {
        return null;
    }
    @Override
    public List<Immeuble> getAllImmeubleByResidence() {
        return null;
    }
}
