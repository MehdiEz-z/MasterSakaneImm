package org.example.appartementservice.controllers.immeubles.vms.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.appartementservice.models.entities.Immeuble;
import org.example.appartementservice.models.entities.Residence;
public record ImmeubleRequestVM(
    @NotBlank(message = "Le Choix de la Résidence est Obligatoire")
    String residence,
    @NotBlank(message = "Le Numero de l'immeuble' est Obligatoire")
    @Size(min = 4, max = 15, message = "Le Numero de l'immeuble doit avoir au minimum 5 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Numero de l'immeuble ne doit pas contenir des caractères spéciaux autres que \"-\"")
    String numero
) {
    public Immeuble toImmeuble(){
        return Immeuble.builder()
                .numero(numero)
                .residence(Residence.builder()
                        .reference(residence)
                        .build())
                .build();
    }
}
