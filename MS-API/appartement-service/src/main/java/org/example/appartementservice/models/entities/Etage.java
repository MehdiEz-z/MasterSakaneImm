package org.example.appartementservice.models.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
@EntityListeners({AuditingEntityListener.class})
public class Etage {
    @Id @Column(unique = true)
    private String reference;
    private String numero;
    @ManyToOne
    private Immeuble immeuble;
    @OneToMany(mappedBy = "etage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appartement> appartements;
    @OneToMany(mappedBy = "etage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Magasin> magasins;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
