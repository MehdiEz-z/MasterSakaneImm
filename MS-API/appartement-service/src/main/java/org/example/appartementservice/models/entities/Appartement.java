package org.example.appartementservice.models.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.appartementservice.models.enums.StatusAppEtMag;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
@EntityListeners({AuditingEntityListener.class})
public class Appartement {
    @Id @Column(unique = true)
    private String reference;
    private String numero;
    private String description;
    private String titreFoncier;
    private double superficieUtile;
    private double superficieTerrasse;
    private double prixGlobalInitial;
    private double prixMetreCarre;
    private int nombreChambre;
    private int nombreSalon;
    private int nombreCuisine;
    private int nombreSalleDeBain;
    private int nombrePlacard;
    private int nombreBalcon;
    @ManyToOne
    private Etage etage;
    @Enumerated(EnumType.STRING)
    private StatusAppEtMag status;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
