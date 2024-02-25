package org.example.clientservice.models.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.*;
import org.example.clientservice.models.enums.Methode;
import org.example.clientservice.models.enums.SituationFamiliale;
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
public class Client {
    @Id @Column(unique = true)
    private String reference;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String lieuNaissance;
    private String nomPere;
    private String nomMere;
    private String adresse;
    @Column(unique = true)
    private String cin;
    private LocalDate cinValidite;
    private String telephone;
    private String nationalite;
    @Column(unique = true)
    private String email;
    private String profession;
    private SituationFamiliale situationFamiliale;
    private Methode methode;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}