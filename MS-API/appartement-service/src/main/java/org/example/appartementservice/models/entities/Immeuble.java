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
public class Immeuble {
    @Id
    private String reference;
    private String numero;
    @ManyToOne
    private Residence residence;
    @OneToMany(mappedBy = "immeuble")
    private List<Etage> etages;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
