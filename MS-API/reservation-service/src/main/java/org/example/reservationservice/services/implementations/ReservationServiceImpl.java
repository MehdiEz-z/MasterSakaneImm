package org.example.reservationservice.services.implementations;
import org.example.reservationservice.models.entities.Reservation;
import org.example.reservationservice.services.interfaces.ReservationService;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class ReservationServiceImpl implements ReservationService {
    @Override
    public Reservation createReservation(Reservation reservation) {
        return null;
    }
    @Override
    public Reservation getReservationByReference(String reservationReference) {
        return null;
    }
    @Override
    public List<Reservation> getAllReservation() {
        return null;
    }
}
