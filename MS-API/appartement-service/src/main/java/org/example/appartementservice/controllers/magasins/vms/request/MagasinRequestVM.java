package org.example.appartementservice.controllers.magasins.vms.request;
import jakarta.validation.constraints.*;
import org.example.appartementservice.models.entities.Etage;
import org.example.appartementservice.models.entities.Magasin;
import org.example.appartementservice.models.enums.StatusAppEtMag;

public record MagasinRequestVM(
        @NotBlank(message = "Le Choix d'étage est Obligatoire")
        String etage,
        @NotBlank(message = "Le Numero du Magasin est Obligatoire")
        @Size(min = 4, max = 15, message = "Le Numero du Magasin doit avoir au minimum 5 caractères")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Numero du Magasin ne doit pas contenir des caractères spéciaux autres que \"-\"")
        String numero,
        @NotBlank(message = "La Description du Magasin est Obligatoire")
        @Size(min = 19, max = 200, message = "La Description du Magasin doit avoir entre 20 et 200 caractères")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Description du Magasin ne doit pas contenir des caractères spéciaux autres que \"-\"")
        String description,
        @NotBlank(message = "Le Titre Foncier du Magasin est Obligatoire")
        @Size(min = 4, max = 15, message = "Le Titre Foncier du Magasin doit avoir au minimum 5 caractères")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Titre Foncier du Magasin ne doit pas contenir des caractères spéciaux autres que \"-\"")
        String titreFoncier,
        @Min(value =1, message = "La Superficie Utile du Magasin est Obligatoire")
        @Positive(message = "La Superficie Utile du Magasin doit être supérieur a 0")
        @Digits(integer = 10, fraction = 2, message = "La Superficie Utile du Magasin doit être une valeur numérique avec au maximum deux décimales")
        double superficieUtile,
        @Min(value =1, message = "La Superficie Terrasse du Magasin est Obligatoire")
        @Positive(message = "La Superficie Terrasse du Magasin doit être supérieur a 0")
        @Digits(integer = 10, fraction = 2, message = "La Superficie Terrasse du Magasin doit être une valeur numérique avec au maximum deux décimales")
        double superficieMezaznine,
        @Min(value =1, message = "Le Prix de Metre du Magasin est Obligatoire")
        @Positive(message = "Le Prix de Metre doit du Magasin être supérieur a 0")
        @Digits(integer = 10, fraction = 2, message = "Le Prix de Metre du Magasin doit être une valeur numérique avec au maximum deux décimales")
        double prixMetreCarre
) {
    public Magasin toMagasin() {
        return Magasin.builder()
                .numero(numero)
                .description(description)
                .titreFoncier(titreFoncier)
                .superficieUtile(superficieUtile)
                .superficieMezaznine(superficieMezaznine)
                .prixMetreCarre(prixMetreCarre)
                .prixGlobalInitial((superficieUtile + (superficieMezaznine/2)) * prixMetreCarre)
                .etage(Etage.builder()
                        .reference(etage)
                        .build())
                .status(StatusAppEtMag.DISPONIBLE)
                .build();
    }
}
