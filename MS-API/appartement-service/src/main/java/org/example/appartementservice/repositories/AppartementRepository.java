package org.example.appartementservice.repositories;
import org.example.appartementservice.models.entities.Appartement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AppartementRepository extends JpaRepository<Appartement,String> {
    boolean existsByReference(String reference);
    Appartement findByReference(String reference);
    List<Appartement> findAllByEtage_Reference(String reference);
}
