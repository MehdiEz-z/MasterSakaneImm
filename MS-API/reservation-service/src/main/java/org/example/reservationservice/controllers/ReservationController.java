package org.example.reservationservice.controllers;
import jakarta.validation.Valid;
import org.example.reservationservice.controllers.vms.request.ReservationRequestVM;
import org.example.reservationservice.controllers.vms.response.ReservationResponseVM;
import org.example.reservationservice.handlers.response.ResponseMessage;
import org.example.reservationservice.models.entities.Reservation;
import org.example.reservationservice.services.interfaces.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/all")
    public ResponseEntity<ResponseMessage> getAllReservation(){
        List<Reservation> reservations = reservationService.getAllReservation();
        if(reservations.isEmpty()) {
            return ResponseMessage.notFound("Aucune Réservation trouvée");
        }else {
            return ResponseMessage.ok(reservations.stream()
                            .map(ReservationResponseVM::toVM).toList(),
                    "Réservations trouvées avec succès");
        }
    }
    @PostMapping("/")
    public ResponseEntity<ResponseMessage> createReservation(@RequestBody @Valid ReservationRequestVM reservationRequestVM){
        Reservation reservation = reservationRequestVM.toReservation();
        Reservation createdReservation = reservationService.createReservationAppartement(reservation);
        return ResponseMessage.created(ReservationResponseVM.toVM(createdReservation),
                "Reservation créée avec succès");
    }
    @PutMapping("/{reservationReference}/annuler")
    public ResponseEntity<ResponseMessage> annulerReservation(@PathVariable String reservationReference){
        String newStatus = reservationService.annulerReservation(reservationReference);
        return ResponseMessage.ok(newStatus,"Réservation annulée avec succès");
    }
    @PutMapping("/{reservationReference}/confirmer")
    public ResponseEntity<ResponseMessage> confirmerReservation(@PathVariable String reservationReference){
        String newStatus = reservationService.confirmerReservation(reservationReference);
        return ResponseMessage.ok(newStatus,"Réservation confirmée avec succès");
    }
}
