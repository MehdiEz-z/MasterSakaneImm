package org.example.reservationservice.models.model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString @Builder
public class Client {
    private String reference;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
}
