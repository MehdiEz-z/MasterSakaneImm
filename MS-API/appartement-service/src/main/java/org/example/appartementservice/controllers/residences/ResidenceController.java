package org.example.appartementservice.controllers.residences;
import jakarta.validation.Valid;
import org.example.appartementservice.controllers.residences.vms.request.ResidenceRequestVM;
import org.example.appartementservice.controllers.residences.vms.response.ResidenceResponseVM;
import org.example.appartementservice.handlers.response.ResponseMessage;
import org.example.appartementservice.models.entities.Residence;
import org.example.appartementservice.services.interfaces.ResidenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/residences")
public class ResidenceController {
    private final ResidenceService residenceService;
    public ResidenceController(ResidenceService residenceService) {
        this.residenceService = residenceService;
    }
    @GetMapping("/{reference}")
    @PreAuthorize("hasAnyAuthority('ADMIN','COMMERCIAL','CLIENT')")
    public ResponseEntity<ResponseMessage> getResidenceByReference(@PathVariable String reference) {
        Residence residence = residenceService.getResidenceByReference(reference);
        return ResponseMessage.ok(ResidenceResponseVM.toVM(residence),
                "Résidence trouvée avec succès");
    }
    @GetMapping("/{nom}")
    @PreAuthorize("hasAnyAuthority('ADMIN','COMMERCIAL','CLIENT')")
    public ResponseEntity<ResponseMessage> getResidenceByNom(@PathVariable String nom) {
        Residence residence = residenceService.getResidenceByNom(nom);
        return ResponseMessage.ok(ResidenceResponseVM.toVM(residence),
                "Résidence trouvée avec succès");
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','COMMERCIAL','CLIENT')")
    public ResponseEntity<ResponseMessage> getAllResidences() {
        List<Residence> residences = residenceService.getAllResidences();
        if(residences.isEmpty()) {
            return ResponseMessage.notFound("Aucune Résidence trouvée");
        }else {
            return ResponseMessage.ok(residences.stream()
                            .map(ResidenceResponseVM::toVM).toList(),
                    "Résidences trouvées avec succès");
        }
    }
    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> createResidence(@RequestBody @Valid ResidenceRequestVM residenceRequestVM){
        Residence residence = residenceRequestVM.toResidence();
        Residence createdResidence = residenceService.createResidence(residence);
        return ResponseMessage.created(ResidenceResponseVM.toVM(createdResidence),
                "Résidence créée avec succès");
    }
}
