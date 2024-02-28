package org.example.reservationservice.models.model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString @Builder
public class Appartement {
    private String reference;
    private String residence;
    private String immeuble;
    private String etage;
    private String numero;
    private double superficieUtile;
    private double superficieTerrasse;
    private double prixMetreCarre;
    private String status;
}
