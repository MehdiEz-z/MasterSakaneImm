package org.example.appartementservice.repositories;
import org.example.appartementservice.models.entities.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface ResidenceRepository extends JpaRepository<Residence, String>{
    boolean existsByReference(String reference);
    Optional<Residence> findByReference(String reference);
    Optional<Residence> findByNom(String reference);
}
