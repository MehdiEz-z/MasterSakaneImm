package org.example.reservationservice.controllers.vms.response;
import org.example.reservationservice.models.entities.Reservation;
import java.time.LocalDate;
public record ReservationResponseVM(
    String nomClient,

    String reference,
    String prenomClient,
    String residence,
    String immeuble,
    String etage,
    String appartement,
    LocalDate dateReservation,
    double prixMetreCarre,
    double prixDeVente,
    String statusReservation
) {
    public static ReservationResponseVM toVM(Reservation reservation) {
        return new ReservationResponseVM(
            reservation.getClient().getNom(),
            reservation.getReference(),
            reservation.getClient().getPrenom(),
            reservation.getAppartement().getResidence(),
            reservation.getAppartement().getImmeuble(),
            reservation.getAppartement().getEtage(),
            reservation.getAppartement().getNumero(),
            reservation.getDateReservation(),
            reservation.getPrixMetreCarre(),
            reservation.getPrixDeVente(),
            reservation.getStatus().name()
        );
    }
}
