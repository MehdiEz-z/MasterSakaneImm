package org.example.reservationservice.models.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class Magasin {
    private String reference;
    private String numero;
    private double prixGlobalInitial;
    private double prixMetreCarre;
    private String etage;
    private String status;
}
