package org.example.reservationservice.services.interfaces;
import org.example.reservationservice.models.entities.Reservation;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    Reservation getReservationByReference(String reservationReference);
    List<Reservation> getAllReservation();
}
