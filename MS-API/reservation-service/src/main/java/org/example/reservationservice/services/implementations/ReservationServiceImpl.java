package org.example.reservationservice.services.implementations;
import org.example.reservationservice.clients.AppartementRest;
import org.example.reservationservice.clients.ClientRest;
import org.example.reservationservice.handlers.exceptionHandler.OperationsException;
import org.example.reservationservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.reservationservice.models.entities.Reservation;
import org.example.reservationservice.models.model.Appartement;
import org.example.reservationservice.models.model.Client;
import org.example.reservationservice.repositories.ReservationRepository;
import org.example.reservationservice.services.interfaces.ReservationService;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public Reservation createReservationAppartement(Reservation reservation) {
        Appartement appartement = appartementRest.getAppartementByReference(reservation.getReferenceAppartement());
        if (("EN_ATTENTE").equals(appartement.getStatus())){
            throw new OperationsException("L'appartement" + " '" + reservation.getReferenceAppartement() + "'" +" est en cours de réservation par un autre client");
        }
        String reference = generateReferenceReservation(reservation);
        LocalDate dateReservation= reservation.getDateReservation();
        LocalDate dateMaintenant = LocalDate.now();
        if(dateReservation.isBefore(dateMaintenant)){
            throw new OperationsException("La date de réservation ne peut pas être dans le passé");
        }
        reservation.setReference(reference);
        reservation.setAppartement(appartement);
        reservation.setClient(clientRest.getClientByReference(reservation.getReferenceClient()));
        reservation.setPrixDeVente(( appartement.getSuperficieUtile() + (appartement.getSuperficieTerrasse() * 2 )) * reservation.getPrixMetreCarre());
        reservationRepository.save(reservation);
        appartementRest.updateAppartementStatus(reservation.getReferenceAppartement(), "EN_ATTENTE");
        return reservation;
    }
    @Override
    public Reservation getReservationByReference(String reservationReference) {
        Optional<Reservation> optionalReservation = reservationRepository.findByReference(reservationReference);
        if (optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            Client client = clientRest.getClientByReference(reservation.getReferenceClient());
            Appartement appartement = appartementRest.getAppartementByReference(reservation.getReferenceAppartement());
            reservation.setClient(client);
            reservation.setAppartement(appartement);
            return reservation;
        }
        throw new ResourcesNotFoundException("La réservation"+ " '" + reservationReference + "'" + " n'existe pas");
    }
    @Override
    public List<Reservation> getAllReservation() {
        return Collections.emptyList();
    }
    public String generateReferenceReservation(Reservation reservation) {
        String appartementReference = reservation.getReferenceAppartement();
        String clientReference = reservation.getReferenceClient();
        int suffix = 1;
        String reference = appartementReference + '-' + clientReference + "-RSV-" + suffix;
        while (reservationRepository.existsByReference(reference)) {
            suffix++;
            reference = appartementReference + '-' + clientReference + "-RSV-" + suffix;
        }
        return reference;
    }
}
