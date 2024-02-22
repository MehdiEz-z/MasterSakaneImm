package org.example.appartementservice.services.implementations;
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
        return null;
    }
    @Override
    public Magasin getMagasinByReference(String magasinReference) {
        return null;
    }
    @Override
    public List<Magasin> getAllMagasinByEtage(String etageReference) {
        return null;
    }
    public String generateReferenceMagasin(Magasin magasin) {
        return null;
    }
}
