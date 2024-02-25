package org.example.clientservice.controllers.clients.vms.response;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.clientservice.models.entities.Client;
import java.time.LocalDate;
public record ClientResponseVM(
    String reference,
    String nom,
    String prenom,
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dateNaissance,
    String lieuNaissance,
    String nomPere,
    String nomMere,
    String adresse,
    String cin,
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate cinValidite,
    String telephone,
    String nationalite,
    String email,
    String profession,
    String situationFamiliale,
    String methode
) {
    public static ClientResponseVM toVM(Client client){
        return new ClientResponseVM(
                client.getReference(),
                client.getNom(),
                client.getPrenom(),
                client.getDateNaissance(),
                client.getLieuNaissance(),
                client.getNomPere(),
                client.getNomMere(),
                client.getAdresse(),
                client.getCin(),
                client.getCinValidite(),
                client.getTelephone(),
                client.getNationalite(),
                client.getEmail(),
                client.getProfession(),
                client.getSituationFamiliale().name(),
                client.getMethode().name()
        );
    }
}
