package org.example.appartementservice.controllers.etages.vms.response;
import org.example.appartementservice.models.entities.Etage;
public record EtageResponseVM(
    String numero,
    String immeuble,
    String residence
) {
    public static EtageResponseVM toVM(Etage etage) {
        return new EtageResponseVM(
            etage.getNumero(),
            etage.getImmeuble().getNumero(),
            etage.getImmeuble().getResidence().getNom()
        );
    }
}
