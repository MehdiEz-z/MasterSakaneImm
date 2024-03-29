package org.example.appartementservice.controllers.appartements;
import jakarta.validation.Valid;
import org.example.appartementservice.controllers.appartements.vms.request.AppartementRequestVM;
import org.example.appartementservice.controllers.appartements.vms.response.AppartementResponseVM;
import org.example.appartementservice.handlers.response.ResponseMessage;
import org.example.appartementservice.models.entities.Appartement;
import org.example.appartementservice.services.interfaces.AppartementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/appartements")
public class AppartementController {
    private final AppartementService appartementService;
    public AppartementController(AppartementService appartementService) {
        this.appartementService = appartementService;
    }
    @GetMapping("{reference}")
    @PreAuthorize("hasAnyAuthority('ADMIN','COMMERCIAL','CLIENT')")
    public AppartementResponseVM getAppartementByReference(@PathVariable String reference) {
        Appartement appartement = appartementService.getAppartementByReference(reference);
        return AppartementResponseVM.toVM(appartement);
    }
    @GetMapping("/{etageReference}/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','COMMERCIAL','CLIENT')")
    public ResponseEntity<ResponseMessage> getAllAppartementByEtage(@PathVariable String etageReference) {
        List<Appartement> appartements = appartementService.getAllAppartementByEtage(etageReference);
        if(appartements.isEmpty()) {
            return ResponseMessage.notFound("Aucun Appartement trouvé");
        }else {
            return ResponseMessage.ok(appartements.stream()
                            .map(AppartementResponseVM::toVM).toList(),
                    "Appartements trouvés avec succès");
        }
    }
    @PutMapping("/{reference}/status/{status}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void updateAppartementStatus(@PathVariable String reference, @PathVariable String status) {
        appartementService.updateAppartementStatus(reference, status);
    }
    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> createAppartement(@RequestBody @Valid AppartementRequestVM appartementRequestVM){
        Appartement appartement = appartementRequestVM.toAppartement();
        Appartement createdAppartement = appartementService.createAppartement(appartement);
        return ResponseMessage.created(AppartementResponseVM.toVM(createdAppartement),
                "Appartement créé avec succès");
    }
}
