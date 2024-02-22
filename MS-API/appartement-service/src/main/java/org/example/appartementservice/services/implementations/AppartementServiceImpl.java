package org.example.appartementservice.services.implementations;
import org.example.appartementservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.appartementservice.models.entities.Appartement;
import org.example.appartementservice.models.entities.Etage;
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
        checkEtageExists(appartement.getEtage().getReference());
        String reference = generateReferenceAppartement(appartement);
        appartement.setReference(reference);
        return appartementRepository.save(appartement);
    }
    @Override
    public Appartement getAppartementByReference(String appartementReference) {
        Appartement foundAppartement = appartementRepository.findByReference(appartementReference);
        if(foundAppartement == null){
            throw new ResourcesNotFoundException("L'Appartement \"" + appartementReference + "\" n'existe pas");
        }
        return foundAppartement;
    }
    @Override
    public List<Appartement> getAllAppartementByEtage(String etageReference) {
        checkEtageExists(etageReference);
        return appartementRepository.findAllByEtage_Reference(etageReference);
    }
    public String generateReferenceAppartement(Appartement appartement) {
        String etageReference = appartement.getEtage().getReference();
        checkEtageExists(etageReference);
        int suffix = 1;
        String reference = etageReference + "-APT-" + suffix;
        while (appartementRepository.existsByReference(reference)) {
            suffix++;
            reference = etageReference + "-APT-" + suffix;
        }
        return reference;
    }
    private void checkEtageExists(String etageReference) {
        Etage etage = etageService.getEtageByReference(etageReference);
        if (etage == null) {
            throw new ResourcesNotFoundException("L'Etage \"" + etageReference + "\" n'existe pas");
        }
    }
}
