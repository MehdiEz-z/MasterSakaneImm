package org.example.reservationservice.models.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class Client {
    private String reference;
    private String nom;
    private String prenom;
    private String telephone;
}
