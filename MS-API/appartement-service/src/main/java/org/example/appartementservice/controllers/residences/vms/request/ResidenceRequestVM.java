package org.example.appartementservice.controllers.residences.vms.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.appartementservice.models.entities.Residence;

public record ResidenceRequestVM(
    @NotBlank(message = "Le Nom du Résidence est Obligatoire")
    @Size(min = 4, max = 15, message = "Le Nom du Résidence doit avoir au minimum 5 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Nom du Résidence ne doit pas contenir des caractères spéciaux autres que \"-\"")
    String nom,
    @NotBlank(message = "La Description du Résidence est Obligatoire")
    @Size(min = 19, max = 200, message = "La Description du Résidence doit avoir entre 20 et 200 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Description du Résidence ne doit pas contenir des caractères spéciaux autres que \"-\"")
    String description,
    @NotBlank(message = "L'Adresse du Résidence est Obligatoire")
    @Size(min = 9, max = 100, message = "L'Adresse du Résidence doit avoir entre 10 et 100 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "L'Adresse du Résidence ne doit pas contenir des caractères spéciaux autres que \"-\"")
    String adresse
) {
    public Residence toResidence() {
        return Residence.builder()
            .nom(nom)
            .description(description)
            .adresse(adresse)
            .build();
    }
}
