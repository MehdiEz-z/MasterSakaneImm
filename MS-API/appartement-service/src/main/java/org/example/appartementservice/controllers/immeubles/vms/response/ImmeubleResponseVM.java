package org.example.appartementservice.controllers.immeubles.vms.response;
import org.example.appartementservice.models.entities.Immeuble;
public record ImmeubleResponseVM(
    String numero,
    String residence,
    String reference
) {
    public static ImmeubleResponseVM toVM(Immeuble immeuble) {
        return new ImmeubleResponseVM(
            immeuble.getNumero(),
            immeuble.getResidence().getNom(),
            immeuble.getReference()
        );
    }
}
