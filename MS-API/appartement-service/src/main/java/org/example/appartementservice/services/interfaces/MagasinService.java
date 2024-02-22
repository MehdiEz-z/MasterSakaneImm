package org.example.appartementservice.services.interfaces;
import org.example.appartementservice.models.entities.Magasin;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface MagasinService {
    Magasin createMagasin(Magasin magasin);
    Magasin getMagasinByReference(String magasinReference);
    List<Magasin> getAllMagasinByEtage(String etageReference);
}
