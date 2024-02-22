package org.example.appartementservice.repositories;
import org.example.appartementservice.models.entities.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MagasinRepository extends JpaRepository<Magasin, String>{
    boolean existsByReference(String reference);
    Magasin findByReference(String reference);
    List<Magasin> findAllByEtage_Reference(String reference);
}
