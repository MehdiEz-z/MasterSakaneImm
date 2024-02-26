package org.example.reservationservice.services.implementations;
import org.example.reservationservice.clients.AppartementRest;
import org.example.reservationservice.clients.ClientRest;
import org.example.reservationservice.handlers.exceptionHandler.ResourcesNotFoundException;
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
        checkAppartementExists(reservation.getReferenceAppartement());
        String appartementReference = reservation.getReferenceAppartement();
        checkClientExists(reservation.getReferenceClient());
        String clientReference = reservation.getReferenceClient();
        int suffix = 1;
        String reference = appartementReference + '-' + clientReference + "-RSV-" + suffix;
        while (reservationRepository.existsByReference(reference)) {
            suffix++;
            reference = appartementReference + '-' + clientReference + "-RSV-" + suffix;
        }
        return reference;
    }
    private void checkAppartementExists(String appartementReference) {
        if (appartementRest.getAppartementByReference(appartementReference) == null) {
            throw new ResourcesNotFoundException("L'Appartement \"" + appartementReference + "\" n'existe pas");
        }
    }
    private void checkClientExists(String clientReference) {
        if (clientRest.getClientByReference(clientReference) == null) {
            throw new ResourcesNotFoundException("Le Client \"" + clientReference + "\" n'existe pas");
        }
    }
}
