package org.example.appartementservice.repositories;
import org.example.appartementservice.models.entities.Appartement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AppartementRepository extends JpaRepository<Appartement,String> {
}
