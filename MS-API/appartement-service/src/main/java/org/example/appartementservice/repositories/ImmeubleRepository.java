package org.example.appartementservice.repositories;
import org.example.appartementservice.models.entities.Immeuble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ImmeubleRepository extends JpaRepository<Immeuble, String> {
    boolean existsByReference(String reference);
    Immeuble findByReference(String reference);
    List<Immeuble> findAllByResidence_Reference(String reference);
}
