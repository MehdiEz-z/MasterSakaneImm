package org.example.appartementservice.repositories;
import org.example.appartementservice.models.entities.Immeuble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ImmeubleRepository extends JpaRepository<Immeuble, String> {
    boolean existsByReference(String reference);
    Immeuble findByReferenceAndResidence_Reference(String reference, String referenceResidence);
}
