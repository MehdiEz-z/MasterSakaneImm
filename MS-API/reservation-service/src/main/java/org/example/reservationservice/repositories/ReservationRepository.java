package org.example.reservationservice.repositories;
import org.example.reservationservice.models.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    boolean existsByReference(String reference);
    Optional<Reservation> findByReference(String reference);
}
