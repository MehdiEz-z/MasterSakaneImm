package org.example.reservationservice.controllers.vms.request;
import jakarta.validation.constraints.*;
import org.example.reservationservice.models.entities.Reservation;
import org.example.reservationservice.models.enums.StatusReservation;
import java.time.LocalDate;
public record ReservationRequestVM(
    @NotBlank(message = "La Réference d'appartement est Obligatoire")
    String appartementReference,
    @NotBlank(message = "La Réference du Client est Obligatoire")
    String clientReference,
    @Min(value =1, message = "Le Prix de Metre est Obligatoire")
    @Positive(message = "Le Prix de Metre doit être supérieur a 0")
    @Digits(integer = 10, fraction = 2, message = "Le Prix de Metre doit être une valeur numérique avec au maximum deux décimales")
    double prixMetreCarre
) {
    public Reservation toReservation(){
        return Reservation.builder()
                .referenceAppartement(appartementReference)
                .referenceClient(clientReference)
                .dateReservation(LocalDate.now())
                .prixMetreCarre(prixMetreCarre)
                .status(StatusReservation.EN_ATTENTE)
                .build();
    }

}
