package org.example.appartementservice.services.implementations;
import org.example.appartementservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.appartementservice.models.entities.Etage;
import org.example.appartementservice.models.entities.Immeuble;
import org.example.appartementservice.repositories.EtageRepository;
import org.example.appartementservice.services.interfaces.EtageService;
import org.example.appartementservice.services.interfaces.ImmeubleService;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class EtageServiceImpl implements EtageService {
    private final EtageRepository etageRepository;
    private final ImmeubleService immeubleService;
    public EtageServiceImpl(EtageRepository etageRepository, ImmeubleService immeubleService) {
        this.etageRepository = etageRepository;
        this.immeubleService = immeubleService;
    }
    @Override
    public Etage createEtage(Etage etage) {
        checkImmeubleExists(etage.getImmeuble().getReference());
        String reference = generateReferenceEtage(etage);
        etage.setReference(reference);
        return etageRepository.save(etage);
    }
    @Override
    public Etage getEtageByReference(String etageReference) {
        Etage foundEtage = etageRepository.findByReference(etageReference);
        if(foundEtage == null){
            throw new ResourcesNotFoundException("L'Etage \"" + etageReference + "\" n'existe pas");
        }
        return foundEtage;
    }
    @Override
    public List<Etage> getAllEtageByImmeuble(String immeubleReference) {
        checkImmeubleExists(immeubleReference);
        return etageRepository.findAllByImmeuble_Reference(immeubleReference);
    }
    public String generateReferenceEtage(Etage etage) {
        String immeubleReference = etage.getImmeuble().getReference();
        checkImmeubleExists(immeubleReference);
        int suffix = 1;
        String reference = immeubleReference + "-ETG-" + suffix;
        while (etageRepository.existsByReference(reference)) {
            suffix++;
            reference = immeubleReference + "-ETG-" + suffix;
        }
        return reference;
    }
    private void checkImmeubleExists(String immeubleReference) {
        Immeuble immeuble = immeubleService.getImmeubleByReference(immeubleReference);
        if (immeuble == null) {
            throw new ResourcesNotFoundException("L'Immeuble \"" + immeubleReference + "\" n'existe pas");
        }
    }
}
