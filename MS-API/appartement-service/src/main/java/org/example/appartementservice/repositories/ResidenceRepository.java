package org.example.appartementservice.repositories;
import org.example.appartementservice.models.entities.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ResidenceRepository extends JpaRepository<Residence, String>{
}
