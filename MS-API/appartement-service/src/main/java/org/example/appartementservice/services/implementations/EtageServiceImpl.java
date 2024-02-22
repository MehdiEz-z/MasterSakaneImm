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
        return null;
    }
    @Override
    public Etage getEtageByReferenceAndImmeuble(String etageReference, String immeubleReference) {
        return null;
    }
    @Override
    public List<Etage> getAllEtageByImmeuble(String immeubleReference) {
        return null;
    }
    public String generateReferenceEtage(Etage etage) {
        String immeubleReference = etage.getImmeuble().getReference();
        String residenceReference = etage.getImmeuble().getResidence().getReference();
        checkImmeubleExists(immeubleReference, residenceReference);
        int suffix = 1;
        String reference = immeubleReference + "-ETG-" + suffix;
        while (etageRepository.existsByReference(reference)) {
            suffix++;
            reference = immeubleReference + "-ETG-" + suffix;
        }
        return reference;
    }
    private void checkImmeubleExists(String immeubleReference, String residenceReference) {
        Immeuble immeuble = immeubleService.getImmeubleByReferenceAndResidence(immeubleReference, residenceReference);
        if (immeuble == null) {
            throw new ResourcesNotFoundException("L'Immeuble \"" + immeubleReference + "\" n'existe pas");
        }
    }
}
