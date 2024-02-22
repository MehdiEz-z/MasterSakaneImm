package org.example.appartementservice.services.implementations;
import org.example.appartementservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.appartementservice.models.entities.Etage;
import org.example.appartementservice.models.entities.Magasin;
import org.example.appartementservice.repositories.MagasinRepository;
import org.example.appartementservice.services.interfaces.EtageService;
import org.example.appartementservice.services.interfaces.MagasinService;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class MagasinServiceImpl implements MagasinService {
    private final MagasinRepository magasinRepository;
    private final EtageService etageService;
    public MagasinServiceImpl(MagasinRepository magasinRepository, EtageService etageService) {
        this.magasinRepository = magasinRepository;
        this.etageService = etageService;
    }
    @Override
    public Magasin createMagasin(Magasin magasin) {
        checkEtageExists(magasin.getEtage().getReference());
        String reference = generateReferenceMagasin(magasin);
        magasin.setReference(reference);
        return magasinRepository.save(magasin);
    }
    @Override
    public Magasin getMagasinByReference(String magasinReference) {
        Magasin foundMagasin = magasinRepository.findByReference(magasinReference);
        if(foundMagasin == null){
            throw new ResourcesNotFoundException("Le Magasin \"" + magasinReference + "\" n'existe pas");
        }
        return foundMagasin;
    }
    @Override
    public List<Magasin> getAllMagasinByEtage(String etageReference) {
        checkEtageExists(etageReference);
        return magasinRepository.findAllByEtage_Reference(etageReference);
    }
    public String generateReferenceMagasin(Magasin magasin) {
        String etageReference = magasin.getEtage().getReference();
        checkEtageExists(etageReference);
        int suffix = 1;
        String reference = etageReference + "-MAG-" + suffix;
        while (magasinRepository.existsByReference(reference)) {
            suffix++;
            reference = etageReference + "-MAG-" + suffix;
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
