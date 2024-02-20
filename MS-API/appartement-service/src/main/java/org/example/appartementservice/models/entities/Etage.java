package org.example.appartementservice.models.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
@EntityListeners({AuditingEntityListener.class})
public class Etage {
    @Id
    private String reference;
    private String numero;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
