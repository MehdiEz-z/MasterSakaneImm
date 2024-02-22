package org.example.appartementservice.controllers.appartements.vms.request;
import jakarta.validation.constraints.*;
import org.example.appartementservice.models.entities.Appartement;
import org.example.appartementservice.models.entities.Etage;
import org.example.appartementservice.models.enums.StatusAppEtMag;
public record AppartementRequestVM(
    @NotBlank(message = "Le Choix d'étage est Obligatoire")
    String etage,
    @NotBlank(message = "Le Numero d'appartement est Obligatoire")
    @Size(min = 4, max = 15, message = "Le Numero d'appartement doit avoir au minimum 5 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Numero d'appartement ne doit pas contenir des caractères spéciaux autres que \"-\"")
    String numero,
    @NotBlank(message = "La Description d'appartement' est Obligatoire")
    @Size(min = 19, max = 200, message = "La Description d'appartement' doit avoir entre 20 et 200 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Description d'appartement ne doit pas contenir des caractères spéciaux autres que \"-\"")
    String description,
    @NotBlank(message = "Le Titre Foncier d'appartement' est Obligatoire")
    @Size(min = 4, max = 15, message = "Le Titre Foncier d'appartement' doit avoir au minimum 5 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Titre Foncier d'appartement ne doit pas contenir des caractères spéciaux autres que \"-\"")
    String titreFoncier,
    @Min(value =1, message = "La Superficie Utile est Obligatoire")
    @Positive(message = "La Superficie Utile doit être supérieur a 0")
    @Digits(integer = 10, fraction = 2, message = "La Superficie Utile doit être une valeur numérique avec au maximum deux décimales")
    double superficieUtile,
    @Min(value =1, message = "La Superficie Terrasse est Obligatoire")
    @Positive(message = "La Superficie Terrasse doit être supérieur a 0")
    @Digits(integer = 10, fraction = 2, message = "La Superficie Terrasse doit être une valeur numérique avec au maximum deux décimales")
    double superficieTerrasse,
    @Min(value =1, message = "Le Prix de Metre est Obligatoire")
    @Positive(message = "Le Prix de Metre doit être supérieur a 0")
    @Digits(integer = 10, fraction = 2, message = "Le Prix de Metre doit être une valeur numérique avec au maximum deux décimales")
    double prixMetreCarre,
    @Min(value =1, message = "Le Nombre de Chambre est Obligatoire")
    @Positive(message = "Le Nombre de Chambre doit être supérieur a 0")
    @Digits(integer = 10, fraction = 0, message = "Le Nombre de Chambre doit être un nombre entier positif")
    int nombreChambre,
    @Min(value =1, message = "Le Nombre de Salon est Obligatoire")
    @Positive(message = "Le Nombre de Salon doit être supérieur a 0")
    @Digits(integer = 10, fraction = 0, message = "Le Nombre de Salon être un nombre entier positif")
    int nombreSalon,
    @Min(value =1, message = "Le Nombre de Cuisine est Obligatoire")
    @Positive(message = "Le Nombre de Cuisine doit être supérieur a 0")
    @Digits(integer = 10, fraction = 0, message = "Le Nombre de Cuisine doit être un nombre entier positif")
    int nombreCuisine,
    @Min(value =1, message = "Le Nombre de Salle de Bain est Obligatoire")
    @Positive(message = "Le Nombre de Salle de Bain doit être supérieur a 0")
    @Digits(integer = 10, fraction = 0, message = "Le Nombre de Salle de Bain doit être un nombre entier positif")
    int nombreSalleDeBain,
    @Min(value =1, message = "Le Nombre de Palacard est Obligatoire")
    @Positive(message = "Le Nombre de Placard doit être supérieur a 0")
    @Digits(integer = 10, fraction = 0, message = "Le Nombre de Chambre doit être un nombre entier positif")
    int nombrePlacard,
    @Min(value =1, message = "Le Nombre de Balcon est Obligatoire")
    @Positive(message = "Le Nombre de Balcon doit être supérieur a 0")
    @Digits(integer = 10, fraction = 0, message = "Le Nombre de Balcon doit être un nombre entier positif")
    int nombreBalcon
) {
    public Appartement toAppartement() {
        return Appartement.builder()
            .numero(numero)
            .description(description)
            .titreFoncier(titreFoncier)
            .superficieUtile(superficieUtile)
            .superficieTerrasse(superficieTerrasse)
            .prixMetreCarre(prixMetreCarre)
            .prixGlobalInitial((superficieUtile + (superficieTerrasse/2)) * prixMetreCarre)
            .nombreChambre(nombreChambre)
            .nombreSalon(nombreSalon)
            .nombreCuisine(nombreCuisine)
            .nombreSalleDeBain(nombreSalleDeBain)
            .nombrePlacard(nombrePlacard)
            .nombreBalcon(nombreBalcon)
            .etage(Etage.builder()
                    .reference(etage)
                    .build())
            .status(StatusAppEtMag.DISPONIBLE)
            .build();
    }
}
