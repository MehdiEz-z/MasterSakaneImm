package org.example.appartementservice.controllers.residences.vms.response;
import org.example.appartementservice.models.entities.Residence;
public record ResidenceResponseVM(
    String reference,
    String nom,
    String description,
    String adresse
) {
    public static ResidenceResponseVM toVM(Residence residence) {
        return new ResidenceResponseVM(
            residence.getReference(),
            residence.getNom(),
            residence.getDescription(),
            residence.getAdresse()
        );
    }
}
