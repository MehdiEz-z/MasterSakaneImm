package org.example.appartementservice.controllers.magasins;
import jakarta.validation.Valid;
import org.example.appartementservice.controllers.magasins.vms.request.MagasinRequestVM;
import org.example.appartementservice.controllers.magasins.vms.response.MagasinResponseVM;
import org.example.appartementservice.handlers.response.ResponseMessage;
import org.example.appartementservice.models.entities.Magasin;
import org.example.appartementservice.services.interfaces.MagasinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/magasins")
public class MagasinController {
    private final MagasinService magasinService;
    public MagasinController(MagasinService magasinService) {
        this.magasinService = magasinService;
    }
    @GetMapping("{reference}")
    public ResponseEntity<ResponseMessage> getMagasinByReference(@PathVariable String reference) {
        Magasin magasin = magasinService.getMagasinByReference(reference);
        return ResponseMessage.ok(MagasinResponseVM.toVM(magasin),
                "Magasin trouvé avec succès");
    }
    @GetMapping("/{etageReference}/all")
    public ResponseEntity<ResponseMessage> getAllMagasinByEtage(@PathVariable String etageReference) {
        List<Magasin> magasins = magasinService.getAllMagasinByEtage(etageReference);
        if(magasins.isEmpty()) {
            return ResponseMessage.notFound("Aucun Magasin trouvé");
        }else {
            return ResponseMessage.ok(magasins.stream()
                            .map(MagasinResponseVM::toVM).toList(),
                    "Magasins trouvés avec succès");
        }
    }
    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createMagasin(@RequestBody @Valid MagasinRequestVM magasinRequestVM){
        Magasin magasin = magasinRequestVM.toMagasin();
        Magasin createdMagasin = magasinService.createMagasin(magasin);
        return ResponseMessage.created(MagasinResponseVM.toVM(createdMagasin),
                "Magasin créé avec succès");
    }
}
