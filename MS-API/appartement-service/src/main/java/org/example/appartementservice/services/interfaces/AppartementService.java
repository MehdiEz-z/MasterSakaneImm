package org.example.appartementservice.services.interfaces;
import org.example.appartementservice.models.entities.Appartement;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface AppartementService {
    Appartement createAppartement(Appartement appartement);
    Appartement getAppartementByReference(String appartementReference);
    List<Appartement> getAllAppartementByEtage(String etageReference);
}
