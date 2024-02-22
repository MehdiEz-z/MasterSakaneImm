package org.example.appartementservice.repositories;
import org.example.appartementservice.models.entities.Etage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EtageRepository extends JpaRepository<Etage, String> {
    boolean existsByReference(String reference);
    Etage findByReference(String reference);
    List<Etage> findAllByImmeuble_Reference(String reference);
}
