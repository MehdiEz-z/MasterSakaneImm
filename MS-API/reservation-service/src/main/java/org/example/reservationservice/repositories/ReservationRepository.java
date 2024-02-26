package org.example.reservationservice.repositories;
import org.example.reservationservice.models.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    boolean existsByReference(String reference);
}
