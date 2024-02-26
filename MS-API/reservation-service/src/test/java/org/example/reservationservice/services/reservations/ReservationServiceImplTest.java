package org.example.reservationservice.services.reservations;
import org.example.reservationservice.clients.AppartementRest;
import org.example.reservationservice.clients.ClientRest;
import org.example.reservationservice.handlers.exceptionHandler.OperationsException;
import org.example.reservationservice.models.entities.Reservation;
import org.example.reservationservice.models.enums.StatusReservation;
import org.example.reservationservice.repositories.ReservationRepository;
import org.example.reservationservice.services.implementations.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
class ReservationServiceImplTest {
    private ReservationServiceImpl reservationService;
    private ReservationRepository reservationRepository;
    private ClientRest clientRest;
    private AppartementRest appartementRest;
    @BeforeEach
    void setUp(){
        reservationRepository = Mockito.mock(ReservationRepository.class);
        clientRest = Mockito.mock(ClientRest.class);
        appartementRest = Mockito.mock(AppartementRest.class);
        reservationService = new ReservationServiceImpl(reservationRepository, clientRest, appartementRest);
    }
    private Reservation createReservationAppartement(){
        return Reservation.builder()
                .reference("RES-1-IMM-1-ETG-1-APT-1-RSV-1")
                .referenceAppartement("ES-1-IMM-1-ETG-1-APT-1")
                .referenceClient("ME-EZZ-96-1")
                .dateReservation(LocalDate.of(2024, 3, 1))
                .status(StatusReservation.EN_ATTENTE)
                .prixMetreCarre(11200.0)
                .prixDeVente(1120000.0)
                .build();
    }
    @Test
    void testGenerateReferenceReservation(){
        Reservation reservation1 = createReservationAppartement();
        Reservation reservation2 = Reservation.builder()
                .referenceAppartement("ES-1-IMM-1-ETG-1-APT-1")
                .referenceClient("ME-EZZ-96-1")
                .dateReservation(LocalDate.of(2024, 3, 1))
                .status(StatusReservation.EN_ATTENTE)
                .prixMetreCarre(11200.0)
                .prixDeVente(1120000.0)
                .build();
        String reference = reservationService.generateReferenceReservation(reservation2);
        assertNotNull(reference);
        assertEquals(reservation1.getReference(), reference);
    }
    @Test
    void testGenerateSecondReservationReference(){
        String reference = "RES-1-IMM-1-ETG-1-APT-1-RSV-1";
        Reservation reservation2 = Reservation.builder()
                .referenceAppartement("ES-1-IMM-1-ETG-1-APT-1")
                .referenceClient("ME-EZZ-96-1")
                .dateReservation(LocalDate.of(2024, 3, 1))
                .status(StatusReservation.EN_ATTENTE)
                .prixMetreCarre(11200.0)
                .prixDeVente(1120000.0)
                .build();
        Mockito.when(reservationRepository.existsByReference(reference)).thenReturn(true);
        String reference2 = reservationService.generateReferenceReservation(reservation2);
        assertNotNull(reference);
        assertEquals(reference, reference2);
    }
    @Test
    void testCheckDateReservationNotInPast(){
        Reservation reservation2 = Reservation.builder()
                .referenceAppartement("ES-1-IMM-1-ETG-1-APT-1")
                .referenceClient("ME-EZZ-96-1")
                .dateReservation(LocalDate.of(2024, 2, 1))
                .status(StatusReservation.EN_ATTENTE)
                .prixMetreCarre(11200.0)
                .prixDeVente(1120000.0)
                .build();
        String exceptedMessage = "La date de réservation ne peut pas être dans le passé";
        Exception exception = assertThrows(OperationsException.class,
                () -> reservationService.createReservation(reservation2));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testCheckClientIfNotExistsAndThrowException(){
        Reservation reservation2 = createReservationAppartement();
        Mockito.when(appartementRest.getAppartementByReference(reservation2.getReferenceAppartement()))
                .thenReturn(reservation2.getAppartement());
        Mockito.when(clientRest.getClientByReference(reservation2.getReferenceClient()))
                .thenReturn(null);
        String exceptedMessage = "Le client \"" + reservation2.getReferenceClient() + "\" n'existe pas";
        Exception exception = assertThrows(OperationsException.class,
                () -> reservationService.createReservation(reservation2));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testCheckAppartementIfNotExistsAndThrowException(){
        Reservation reservation2 = createReservationAppartement();
        Mockito.when(appartementRest.getAppartementByReference(reservation2.getReferenceAppartement()))
                .thenReturn(null);
        Mockito.when(clientRest.getClientByReference(reservation2.getReferenceClient()))
                .thenReturn(reservation2.getClient());
        String exceptedMessage = "L'appartement \"" + reservation2.getReferenceAppartement() + "\" n'existe pas";
        Exception exception = assertThrows(OperationsException.class,
                () -> reservationService.createReservation(reservation2));
        assertEquals(exceptedMessage, exception.getMessage());
    }
    @Test
    void testCreateReservationSuccess(){
        Reservation reservation1 = createReservationAppartement();
        Reservation reservation2 = Reservation.builder()
                .referenceAppartement("ES-1-IMM-1-ETG-1-APT-1")
                .referenceClient("ME-EZZ-96-1")
                .dateReservation(LocalDate.of(2024, 3, 1))
                .status(StatusReservation.EN_ATTENTE)
                .prixMetreCarre(11200.0)
                .prixDeVente(1120000.0)
                .build();
        Mockito.when(appartementRest.getAppartementByReference(reservation2.getReferenceAppartement()))
                .thenReturn(reservation2.getAppartement());
        Mockito.when(clientRest.getClientByReference(reservation2.getReferenceClient()))
                .thenReturn(reservation2.getClient());
        Mockito.when(reservationRepository.save(reservation2)).thenReturn(reservation2);
        Reservation createdReservation = reservationService.createReservation(reservation2);
        assertNotNull(createdReservation);
        assertEquals(reservation1, createdReservation);
    }
}