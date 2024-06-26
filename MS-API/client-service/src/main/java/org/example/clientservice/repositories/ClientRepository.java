package org.example.clientservice.repositories;
import org.example.clientservice.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    boolean existsByReference(String reference);
    boolean existsByCin(String cin);
    boolean existsByEmail(String email);
    Optional<Client> findByReference(String reference);
    Optional<Client> findByCin(String cin);
}
