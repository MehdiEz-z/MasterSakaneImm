package org.example.appartementservice.services.implementations;
import org.example.appartementservice.models.entities.Appartement;
import org.example.appartementservice.repositories.AppartementRepository;
import org.example.appartementservice.services.interfaces.AppartementService;
import org.example.appartementservice.services.interfaces.EtageService;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class AppartementServiceImpl implements AppartementService {
    private final AppartementRepository appartementRepository;
    private final EtageService etageService;
    public AppartementServiceImpl(AppartementRepository appartementRepository1, EtageService etageService) {
        this.appartementRepository = appartementRepository1;
        this.etageService = etageService;
    }
    @Override
    public Appartement createAppartement(Appartement appartement) {
        return null;
    }
    @Override
    public Appartement getAppartementByReference(String appartementReference) {
        return null;
    }
    @Override
    public List<Appartement> getAllAppartementByEtage(String etageReference) {
        return null;
    }
    public String generateReferenceAppartement(Appartement appartement) {
        return null;
    }
}
