package org.example.reservationservice.controllers;
import jakarta.validation.Valid;
import org.example.reservationservice.controllers.vms.request.ReservationRequestVM;
import org.example.reservationservice.controllers.vms.response.ReservationResponseVM;
import org.example.reservationservice.handlers.response.ResponseMessage;
import org.example.reservationservice.models.entities.Reservation;
import org.example.reservationservice.services.interfaces.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping("/{reservationReference}")
    public ResponseEntity<ResponseMessage> getReservationByReference(@PathVariable String reservationReference){
        Reservation reservation = reservationService.getReservationByReference(reservationReference);
        return ResponseMessage.ok(ReservationResponseVM.toVM(reservation),
                "Réservation trouvée avec succès");
    }
    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createReservation(@RequestBody @Valid ReservationRequestVM reservationRequestVM){
        Reservation reservation = reservationRequestVM.toReservation();
        Reservation createdReservation = reservationService.createReservationAppartement(reservation);
        return ResponseMessage.created(ReservationResponseVM.toVM(createdReservation),
                "Reservation créée avec succès");
    }
}
