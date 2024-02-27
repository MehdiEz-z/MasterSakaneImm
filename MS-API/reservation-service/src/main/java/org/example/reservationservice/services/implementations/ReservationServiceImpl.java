package org.example.reservationservice.services.implementations;
import org.example.reservationservice.clients.AppartementRest;
import org.example.reservationservice.clients.ClientRest;
import org.example.reservationservice.handlers.exceptionHandler.OperationsException;
import org.example.reservationservice.handlers.exceptionHandler.ResourcesNotFoundException;
import org.example.reservationservice.models.entities.Reservation;
import org.example.reservationservice.models.model.Appartement;
import org.example.reservationservice.repositories.ReservationRepository;
import org.example.reservationservice.services.interfaces.ReservationService;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
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
    private static final String STATUS_APT = "EN_ATTENTE";
    @Override
    public Reservation createReservationAppartement(Reservation reservation) {
        Appartement appartement = appartementRest.getAppartementByReference(reservation.getReferenceAppartement());
        if (STATUS_APT.equals(appartement.getStatus())){
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
        appartementRest.updateAppartementStatus(reservation.getReferenceAppartement(), STATUS_APT);
        return reservation;
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
    @Override
    public Reservation getReservationByReference(String reservationReference) {
        Optional<Reservation> optionalReservation = reservationRepository.findByReference(reservationReference);
        if (optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            reservation.setClient(clientRest.getClientByReference(reservation.getReferenceClient()));
            reservation.setAppartement(appartementRest.getAppartementByReference(reservation.getReferenceAppartement()));
            return reservation;
        }
        throw new ResourcesNotFoundException("La réservation"+ " '" + reservationReference + "'" + " n'existe pas");
    }
    @Override
    public List<Reservation> getAllReservation() {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations){
            reservation.setClient(clientRest.getClientByReference(reservation.getReferenceClient()));
            reservation.setAppartement(appartementRest.getAppartementByReference(reservation.getReferenceAppartement()));
        }
        return reservations;
    }
    @Override
    public void annulerReservation(String reservationReference) {
        updateAppartementStatus(reservationReference, "DISPONIBLE", "confirmée", "annulée");
    }
    @Override
    public void confirmerReservation(String reservationReference) {
        updateAppartementStatus(reservationReference, "RESERVE", "annulée", "confirmée");
    }
    private void updateAppartementStatus(String reservationReference, String newStatus, String statusMessageOne, String statusMessageTwo) {
        Appartement appartement = appartementRest.getAppartementByReference(reservationReference);
        if ("RESERVE".equals(appartement.getStatus())){
            throw new OperationsException("La réservation '" + reservationReference + "' est déjà " + statusMessageOne);
        }
        if ("ANNULER".equals(appartement.getStatus())){
            throw new OperationsException("La réservation '" + reservationReference + "' est déjà " + statusMessageTwo);
        }
        if (STATUS_APT.equals(appartement.getStatus())){
            appartementRest.updateAppartementStatus(reservationReference, newStatus);
        }
    }
}
