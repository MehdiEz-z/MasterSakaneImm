package org.example.reservationservice.controllers.vms.response;
import org.example.reservationservice.models.entities.Reservation;
import java.time.LocalDate;
public record ReservationResponseVM(
    String reference,
    String nomClient,
    String prenomClient,
    String residence,
    String immeuble,
    String etage,
    String appartement,
    String statusAppartement,
    LocalDate dateReservation,
    double prixMetreCarre,
    double prixDeVente,
    String statusReservation
) {
    public static ReservationResponseVM toVM(Reservation reservation) {
        return new ReservationResponseVM(
            reservation.getReference(),
            reservation.getClient().getNom(),
            reservation.getClient().getPrenom(),
            reservation.getAppartement().getResidence(),
            reservation.getAppartement().getImmeuble(),
            reservation.getAppartement().getEtage(),
            reservation.getAppartement().getNumero(),
            reservation.getAppartement().getStatus(),
            reservation.getDateReservation(),
            reservation.getPrixMetreCarre(),
            reservation.getPrixDeVente(),
            reservation.getStatus().name()
        );
    }
}
