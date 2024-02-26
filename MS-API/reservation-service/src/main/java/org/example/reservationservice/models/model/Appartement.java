package org.example.reservationservice.models.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class Appartement {
    private String numero;
    private double superficieUtile;
    private double superficieTerrasse;
    private double prixGlobal;
    private double prixMetreCarre;
    private String residence;
    private String immeuble;
    private String etage;
    private String status;
}
