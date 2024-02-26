package org.example.reservationservice.models.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.reservationservice.models.enums.StatusReservation;
import org.example.reservationservice.models.model.Appartement;
import org.example.reservationservice.models.model.Client;
import org.example.reservationservice.models.model.Magasin;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
@EntityListeners({AuditingEntityListener.class})
public class Reservation {
    @Id @Column(unique = true)
    private String reference;
    @Transient
    private Appartement appartement;
    private String referenceAppartement;
    @Transient
    private Magasin magasin;
    private String referenceMagasin;
    @Transient
    private Client client;
    private String referenceClient;
    private LocalDate dateReservation;
    @Enumerated(EnumType.STRING)
    private StatusReservation status;
    private double prixMetreCarre;
    private double prixDeVente;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
