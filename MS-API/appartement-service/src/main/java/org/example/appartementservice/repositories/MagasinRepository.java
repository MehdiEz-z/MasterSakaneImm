package org.example.appartementservice.repositories;
import org.example.appartementservice.models.entities.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MagasinRepository extends JpaRepository<Magasin, String>{
}
