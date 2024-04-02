import {Component, OnInit} from '@angular/core';
import {Reservation} from "../../../core/models/reservation/reservation";
import {Observable, throwError} from "rxjs";
import {ReservationService} from "../../../core/services/reservation/reservation.service";
import {KeycloakService} from "keycloak-angular";
import Swal from "sweetalert2";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-list-reservation',
  templateUrl: './list-reservation.component.html',
  styleUrl: './list-reservation.component.css'
})
export class ListReservationComponent implements OnInit{
  reservations!: Observable<Reservation[]>;
  errorMessage!: string;
  constructor(private reservationService: ReservationService, public keycloakService: KeycloakService) {
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  ngOnInit(): void {
    this.reservations = this.reservationService.getReservations().pipe(
      catchError((error) => {
        if (error.status == 404 && error.error.message) {
          this.errorMessage = error.error.message;
        }
        else if(error.status == 500){
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Une erreur interne du serveur est survenue lors de la récupération des réservations.',
            showConfirmButton: false,
            timer: 4500
          }).then(r => {
            setTimeout(() => {
              window.location.reload();
            }, 5000);
          });
        }
        return throwError(() => error);
      })
    );
  }

}
