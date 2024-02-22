package org.example.appartementservice.services.interfaces;
import org.example.appartementservice.models.entities.Etage;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface EtageService {
    Etage createEtage(Etage etage);
    Etage getEtageByReferenceAndImmeuble(String etageReference, String immeubleReference);
    List<Etage> getAllEtageByImmeuble(String immeubleReference);
}
