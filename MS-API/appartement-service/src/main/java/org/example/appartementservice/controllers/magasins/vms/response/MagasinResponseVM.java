package org.example.appartementservice.controllers.magasins.vms.response;
import org.example.appartementservice.models.entities.Magasin;
public record MagasinResponseVM(
        String residence,
        String immeuble,
        String etage,
        String numero,
        String description,
        String titreFoncier,
        double superficieUtile,
        double superficieMezzanine,
        double prixMetreCarre,
        double prixGlobal,
        String status
) {
    public static MagasinResponseVM toVM(Magasin magasin) {
        return new MagasinResponseVM(
                magasin.getEtage().getImmeuble().getResidence().getNom(),
                magasin.getEtage().getImmeuble().getNumero(),
                magasin.getEtage().getNumero(),
                magasin.getNumero(),
                magasin.getDescription(),
                magasin.getTitreFoncier(),
                magasin.getSuperficieUtile(),
                magasin.getSuperficieMezaznine(),
                magasin.getPrixMetreCarre(),
                magasin.getPrixGlobalInitial(),
                magasin.getStatus().name()
        );
    }
}
