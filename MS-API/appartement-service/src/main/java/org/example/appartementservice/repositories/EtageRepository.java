package org.example.appartementservice.repositories;
import org.example.appartementservice.models.entities.Etage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EtageRepository extends JpaRepository<Etage, String> {
}
