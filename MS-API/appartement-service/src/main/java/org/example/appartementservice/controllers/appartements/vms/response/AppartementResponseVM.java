package org.example.appartementservice.controllers.appartements.vms.response;
import org.example.appartementservice.models.entities.Appartement;
public record AppartementResponseVM(
    String etage,
    String numero,
    String description,
    String titreFoncier,
    double superficieUtile,
    double superficieTerrasse,
    double prixMetreCarre,
    double prixGlobal,
    int nombreChambre,
    int nombreSalon,
    int nombreCuisine,
    int nombreSalleDeBain,
    int nombrePlacard,
    int nombreBalcon,
    String status
) {
    public static AppartementResponseVM toVM(Appartement appartement) {
        return new AppartementResponseVM(
            appartement.getEtage().getNumero(),
            appartement.getNumero(),
            appartement.getDescription(),
            appartement.getTitreFoncier(),
            appartement.getSuperficieUtile(),
            appartement.getSuperficieTerrasse(),
            appartement.getPrixMetreCarre(),
            appartement.getPrixGlobalInitial(),
            appartement.getNombreChambre(),
            appartement.getNombreSalon(),
            appartement.getNombreCuisine(),
            appartement.getNombreSalleDeBain(),
            appartement.getNombrePlacard(),
            appartement.getNombreBalcon(),
            appartement.getStatus().name()
        );
    }
}
