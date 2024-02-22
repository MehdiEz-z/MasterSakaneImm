package org.example.appartementservice.controllers.etages.vms.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.appartementservice.models.entities.Etage;
import org.example.appartementservice.models.entities.Immeuble;
public record EtageRequestVM(
    @NotBlank(message = "Le Choix d'immeuble est Obligatoire")
    String immeuble,
    @NotBlank(message = "Le Numero d'étage est Obligatoire")
    @Size(min = 4, max = 15, message = "Le Numero d'étage doit avoir au minimum 5 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Numero d'étage ne doit pas contenir des caractères spéciaux autres que \"-\"")
    String numero
) {
    public Etage toEtage(){
        return Etage.builder()
                .numero(numero)
                .immeuble(Immeuble.builder()
                        .reference(immeuble)
                        .build())
                .build();
    }
}
