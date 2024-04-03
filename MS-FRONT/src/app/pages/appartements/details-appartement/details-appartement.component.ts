import {Component, OnInit} from '@angular/core';
import {Appartement} from "../../../core/models/appartement/appartement";
import {ActivatedRoute, Router} from "@angular/router";
import {AppartementService} from "../../../core/services/appartement/appartement.service";
import {KeycloakService} from "keycloak-angular";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import Swal from "sweetalert2";
import {ReservationService} from "../../../core/services/reservation/reservation.service";

@Component({
  selector: 'app-details-appartement',
  templateUrl: './details-appartement.component.html',
  styleUrl: './details-appartement.component.css'
})
export class DetailsAppartementComponent implements OnInit{
  appartementRef!: string;
  appartement!: Observable<Appartement>;
  errorMessage!: string;
  constructor(private route: ActivatedRoute,
              private appartementService: AppartementService,
              private reservationService: ReservationService,
              public keycloakService: KeycloakService,
              private router: Router) {
    this.appartementRef = this.route.snapshot.params['appartementReference'];
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  goBackEtageDetail(): void {
    this.router.navigateByUrl("etages/"+(this.appartementRef)?.slice(0,17)).then(
      r => console.log("Navigation vers la page de détail de l'étage")
    )
  }
  goReservationCreate(): void {
    this.router.navigateByUrl("reservations/add/"+this.appartementRef).then(
      r => console.log("Navigation vers la page de création de réservation")
    )
  }
  ngOnInit(): void {
    this.appartement = this.appartementService.getAppartement(this.appartementRef).pipe(
      catchError((error) => {
        if (error.status == 404 && error.error.message) {
          this.errorMessage = error.error.message;
        }
        else if(error.status == 500){
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Une erreur interne du serveur est survenue lors de la récupération de l\'appartement.',
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
