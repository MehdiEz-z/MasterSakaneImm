package org.example.appartementservice.controllers.etages;
import org.example.appartementservice.controllers.etages.vms.request.EtageRequestVM;
import org.example.appartementservice.controllers.etages.vms.response.EtageResponseVM;
import org.example.appartementservice.handlers.response.ResponseMessage;
import org.example.appartementservice.models.entities.Etage;
import org.example.appartementservice.services.interfaces.EtageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/etages")
public class EtageController {
    private final EtageService etageService;
    public EtageController(EtageService etageService) {
        this.etageService = etageService;
    }
    @GetMapping("/{reference}")
    public ResponseEntity<ResponseMessage> getEtageByReferenceAndImmeuble(@PathVariable String reference) {
        Etage etage = etageService.getEtageByReference(reference);
        return ResponseMessage.ok(EtageResponseVM.toVM(etage),
                "Etage trouvé avec succès");
    }
    @GetMapping("/{immeubleReference}/all")
    public ResponseEntity<ResponseMessage> getAllEtageByImmeuble(@PathVariable String immeubleReference) {
        List<Etage> etages = etageService.getAllEtageByImmeuble(immeubleReference);
        if(etages.isEmpty()) {
            return ResponseMessage.notFound("Aucun Etage trouvé");
        }else {
            return ResponseMessage.ok(etages.stream()
                            .map(EtageResponseVM::toVM).toList(),
                    "Etages trouvés avec succès");
        }
    }
    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createEtage(@RequestBody EtageRequestVM etageRequestVM){
        Etage etage = etageRequestVM.toEtage();
        Etage createdEtage = etageService.createEtage(etage);
        return ResponseMessage.created(EtageResponseVM.toVM(createdEtage),
                "Etage créé avec succès");

    }
}
