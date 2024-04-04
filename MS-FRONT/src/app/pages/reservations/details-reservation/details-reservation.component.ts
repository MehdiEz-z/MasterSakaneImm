import {Component, OnInit} from '@angular/core';
import {Reservation} from "../../../core/models/reservation/reservation";
import {Observable, throwError} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {ReservationService} from "../../../core/services/reservation/reservation.service";
import {KeycloakService} from "keycloak-angular";
import {catchError} from "rxjs/operators";
import Swal from "sweetalert2";

@Component({
  selector: 'app-details-reservation',
  templateUrl: './details-reservation.component.html',
  styleUrl: './details-reservation.component.css'
})
export class DetailsReservationComponent implements OnInit{
  reservationRef!: string;
  reservation!: Observable<Reservation>;
  errorMessage!: string;
  constructor(private route: ActivatedRoute,
              private reservationService: ReservationService,
              public keycloakService: KeycloakService,
              private router: Router) {
    this.reservationRef = this.route.snapshot.params['reservationReference'];
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  confirmerReservation(){
    Swal.fire({
      title: 'Voulez-vous vraiment confirmer cette réservation?',
      showDenyButton: true,
      confirmButtonText: `Confirmer`,
      denyButtonText: `Ne pas confirmer`,
      customClass: {
        confirmButton: 'btn-reserver'
      }
    }).then((result) => {
      if (result.isConfirmed){
        this.submitConfirmation(this.reservationRef);
      }else if (result.isDenied){
        Swal.fire({
          icon: 'info',
          title: 'Oops...',
          text: 'Confirmation de la réservation annulée',
          showConfirmButton: false,
          timer: 2500
        });
      }
    })
  }
  submitConfirmation(reservationRef: string){
    this.reservationService.confirmerReservation(reservationRef).subscribe({
      next: (response: any) => {
        Swal.fire({
          icon: 'success',
          title: response.message,
          showConfirmButton: false,
          timer: 2500
        }).then(r => {
          this.router.navigateByUrl("/reservations").then(r =>
            console.log("Navigation vers la liste des réservations")
          );
        });
      },
      error: (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: error.error.message,
          showConfirmButton: false,
          timer: 4500
        });
      }
    });
  }
  annulerReservation(){
    Swal.fire({
      title: 'Voulez-vous vraiment annuler cette réservation?',
      showDenyButton: true,
      confirmButtonText: `Annuler`,
      denyButtonText: `Ne pas annuler`,
      customClass: {
        confirmButton: 'btn-reserver'
      }
    }).then((result) => {
      if (result.isConfirmed){
        this.submitAnnulation(this.reservationRef);
      }else if (result.isDenied){
        Swal.fire({
          icon: 'info',
          title: 'Oops...',
          text: 'Annulation de la réservation annulée',
          showConfirmButton: false,
          timer: 2500
        });
      }
    })
  }
  submitAnnulation(reservationRef: string){
    this.reservationService.annulerReservation(reservationRef).subscribe({
      next: (response: any) => {
        Swal.fire({
          icon: 'success',
          title: response.message,
          showConfirmButton: false,
          timer: 2500
        }).then(r => {
          this.router.navigateByUrl("/reservations").then(r =>
            console.log("Navigation vers la liste des réservations")
          );
        });
      },
      error: (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: error.error.message,
          showConfirmButton: false,
          timer: 4500
        });
      }
    });
  }
  ngOnInit(): void {
    this.reservation = this.reservationService.getReservation(this.reservationRef).pipe(
      catchError((error) => {
        if (error.status == 404 && error.error.message) {
          this.errorMessage = error.error.message;
        }
        else if(error.status == 500){
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Une erreur interne du serveur est survenue lors de la récupération de la réservation.',
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
    )
  }

}
