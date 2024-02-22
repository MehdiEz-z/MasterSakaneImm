package org.example.appartementservice.services.interfaces;
import org.example.appartementservice.models.entities.Immeuble;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface ImmeubleService {
    Immeuble createImmeuble(Immeuble residence);
    Immeuble getImmeubleByReference(String immeubleReference);
    List<Immeuble> getAllImmeubleByResidence(String residenceReference);
}
