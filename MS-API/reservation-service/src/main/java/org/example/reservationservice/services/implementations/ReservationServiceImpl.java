package org.example.reservationservice.services.implementations;
import org.example.reservationservice.clients.AppartementRest;
import org.example.reservationservice.clients.ClientRest;
import org.example.reservationservice.models.entities.Reservation;
import org.example.reservationservice.repositories.ReservationRepository;
import org.example.reservationservice.services.interfaces.ReservationService;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ClientRest clientRest;
    private final AppartementRest appartementRest;
    public ReservationServiceImpl(ReservationRepository reservationRepository, ClientRest clientRest, AppartementRest appartementRest) {
        this.reservationRepository = reservationRepository;
        this.clientRest = clientRest;
        this.appartementRest = appartementRest;
    }
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
    public String generateReferenceReservation(Reservation reservation) {
        return null;
    }
}
