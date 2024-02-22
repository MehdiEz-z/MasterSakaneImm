package org.example.appartementservice.services.interfaces;
import org.example.appartementservice.models.entities.Residence;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface ResidenceService {
    Residence createResidence(Residence residence);
    Residence getResidenceByReference(String reference);
    Residence getResidenceByNom(String reference);
    List<Residence> getAllResidences();
}
