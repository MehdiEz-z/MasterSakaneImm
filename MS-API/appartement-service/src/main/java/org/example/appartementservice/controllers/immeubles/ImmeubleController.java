package org.example.appartementservice.controllers.immeubles;
import jakarta.validation.Valid;
import org.example.appartementservice.controllers.immeubles.vms.request.ImmeubleRequestVM;
import org.example.appartementservice.controllers.immeubles.vms.response.ImmeubleResponseVM;
import org.example.appartementservice.handlers.response.ResponseMessage;
import org.example.appartementservice.models.entities.Immeuble;
import org.example.appartementservice.services.interfaces.ImmeubleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/immeubles")
public class ImmeubleController {
    private final ImmeubleService immeubleService;
    public ImmeubleController(ImmeubleService immeubleService) {
        this.immeubleService = immeubleService;
    }
    @GetMapping("/{reference}")

    public ResponseEntity<ResponseMessage> getImmeubleByReferenceAndResidence(@PathVariable String reference) {
        Immeuble immeuble = immeubleService.getImmeubleByReference(reference);
        return ResponseMessage.ok(ImmeubleResponseVM.toVM(immeuble),
                "Immeuble trouvé avec succès");
    }
    @GetMapping("/{residenceReference}/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    public ResponseEntity<ResponseMessage> getAllImmeubleByResidence(@PathVariable String residenceReference) {
        List<Immeuble> immeubles = immeubleService.getAllImmeubleByResidence(residenceReference);
        if(immeubles.isEmpty()) {
            return ResponseMessage.notFound("Aucun Immeuble trouvé");
        }else {
            return ResponseMessage.ok(immeubles.stream()
                            .map(ImmeubleResponseVM::toVM).toList(),
                    "Immeubles trouvés avec succès");
        }
    }
    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> createImmeuble(@RequestBody @Valid ImmeubleRequestVM immeubleRequestVM){
        Immeuble immeuble = immeubleRequestVM.toImmeuble();
        Immeuble createdImmeuble = immeubleService.createImmeuble(immeuble);
        return ResponseMessage.created(ImmeubleResponseVM.toVM(createdImmeuble),
                "Immeuble créé avec succès");

    }
}
