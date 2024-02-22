package org.example.appartementservice.services.implementations;
import org.example.appartementservice.models.entities.Etage;
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
        return null;
    }
}
