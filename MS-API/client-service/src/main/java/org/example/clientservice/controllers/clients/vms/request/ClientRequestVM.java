package org.example.clientservice.controllers.clients.vms.request;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.clientservice.models.entities.Client;
import org.example.clientservice.models.enums.Methode;
import org.example.clientservice.models.enums.SituationFamiliale;

import java.time.LocalDate;
public record ClientRequestVM(
        @NotBlank(message = "Le Nom du Client est Obligatoire")
        @Size(min = 4, max = 15, message = "Le Nom du Client doit avoir au minimum 5 caractères")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Nom du Client ne doit pas contenir des caractères spéciaux autres que \"-\"")
        String nom,
        @NotBlank(message = "Le Prenom du Client est Obligatoire")
        @Size(min = 4, max = 15, message = "Le Prenom du Client doit avoir au minimum 5 caractères")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Prenom du Client ne doit pas contenir des caractères spéciaux autres que \"-\"")
        String prenom,
        @NotNull(message = "La Date de Naissance du Client de Location est obligatoire")
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dateNaissance,
        @NotBlank(message = "Le Lieu de Naissance du Client est Obligatoire")
        @Size(min = 4, max = 15, message = "Le Lieu de Naissance du Client doit avoir au minimum 5 caractères")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Lieu de Naissance du Client ne doit pas contenir des caractères spéciaux autres que \"-\"")
        String lieuNaissance,
        @NotBlank(message = "Le Nom du Pere du Client est Obligatoire")
        @Size(min = 4, max = 15, message = "Le Nom du Pere du Client doit avoir au minimum 5 caractères")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Nom du Pere du Client ne doit pas contenir des caractères spéciaux autres que \"-\"")
        String nomPere,
        @NotBlank(message = "Le Nom du Mere du Client est Obligatoire")
        @Size(min = 4, max = 15, message = "Le Nom du Mere du Client doit avoir au minimum 5 caractères")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Nom du Mere du Client ne doit pas contenir des caractères spéciaux autres que \"-\"")
        String nomMere,
        @NotBlank(message = "L'Adresse du Client est Obligatoire")
        @Size(min = 9, max = 100, message = "L'Adresse du Client doit avoir entre 10 et 100 caractères")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "L'Adresse du Client ne doit pas contenir des caractères spéciaux autres que \"-\"")
        String adresse,
        @NotBlank(message = "La CIN du Client du Client est Obligatoire")
        @Pattern(regexp = "^[a-zA-Z]{2}\\d{0,7}$", message = "La CIN doit commencer par deux lettres suivies de maximum 7 chiffres")
        String cin,
        @NotNull(message = "La Date de Validité de la CIN est obligatoire")
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate cinValidite,
        @NotNull(message = "Le Numero du Téléphone du Client est obligatoire")
        String telephone,
        @NotBlank(message = "La Nationalité du Client est Obligatoire")
        @Size(min = 4, max = 100, message = "La Nationalité du Client doit avoir entre 5 et 100 caractères")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "La Nationalité  du Client ne doit pas contenir des caractères spéciaux autres que \"-\"")
        String nationalite,
        @NotBlank(message = "L'email du Client est Obligatoire")
        @Pattern(regexp = "^[A-Za-z0-9._-]+@(gmail|outlook|hotmail)\\.(com|net|fr)$", message = "Email Invalid")
        String email,
        @NotBlank(message = "La Profession du Client est Obligatoire")
        @Size(min = 4, max = 100, message = "La Profession du Client doit avoir entre 5 et 100 caractères")
        @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "La Profession du Client ne doit pas contenir des caractères spéciaux autres que \"-\"")
        String profession,
        @NotBlank(message = "La Situation Familiale est Obligatoire")
        @Pattern(regexp = "^(MARIE|CELIBATAIRE)$", message = "La Situation Familiale doit être soit MARIE ou bien CELIBATAIRE")
        String situationFamiliale,
        @NotBlank(message = "La Methode est Obligatoire")
        @Pattern(regexp = "^(FACEBOOK|INSTAGRAM|SITEWEB|AMIS|PASSAGER)$", message = "La Methode doit être soit FACEBOOK, INSTAGRAM, SITEWEB, AMIS ou PASSAGER")
        String methode
) {
    public Client toClient(){
        return Client.builder()
                .nom(nom)
                .prenom(prenom)
                .dateNaissance(dateNaissance)
                .lieuNaissance(lieuNaissance)
                .nomPere(nomPere)
                .nomMere(nomMere)
                .adresse(adresse)
                .cin(cin)
                .cinValidite(cinValidite)
                .telephone(telephone)
                .nationalite(nationalite)
                .email(email)
                .profession(profession)
                .situationFamiliale(SituationFamiliale.valueOf(situationFamiliale))
                .methode(Methode.valueOf(methode))
                .build();
    }
}
