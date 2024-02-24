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
import java.time.LocalDateTime;
import java.util.Date;
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
    private Date dateNaissance;
    private String lieuNaissance;
    private String nomPere;
    private String nomMere;
    private String adresse;
    private String cin;
    private Date cinValidite;
    private String telephone;
    private String nationalite;
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
