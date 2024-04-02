import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {KeycloakService} from "keycloak-angular";
import {ActivatedRoute, Router} from "@angular/router";
import {ReservationService} from "../../../core/services/reservation/reservation.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrl: './add-reservation.component.css'
})
export class AddReservationComponent implements OnInit{
  newReservationFormGroup!: FormGroup;
  appartementRef!: string;
  constructor(private route: ActivatedRoute,
              public keycloakService: KeycloakService,
              private reservationService: ReservationService,
              private router: Router,
              private fb: FormBuilder) {
    this.appartementRef = this.route.snapshot.params['appartementReference'];
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  saveReservation() {
let reservation = this.newReservationFormGroup.value;
    console.log(this.newReservationFormGroup)
    this.reservationService.saveReservation(reservation).subscribe({
        next: (response : any) => {
          console.log(response);
          Swal .fire({
            title:'Voulez-vous vraiment réservé cet appartement?',
            showDenyButton: true,
            confirmButtonText: `Réserver`,
            denyButtonText: `Annuler`,
          }).then((result) => {
            if(result.isConfirmed){
              Swal.fire({
                icon: "success",
                title: response.message,
                showConfirmButton: false,
                timer: 2500
              }).then(r => {
                this.router.navigateByUrl("/reservations/"+response.data.reference).then(
                  r => console.log("navigate to reservation " + response.data.reference)
                );
              })
            }else if(result.isDenied){
              Swal.fire({
                icon: 'info',
                title: 'Oops...',
                text: 'Réservation annulée',
                showConfirmButton: false,
                timer: 2500
              });
            }
          })
        },
        error: (error: any) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: error.error.message,
            showConfirmButton: false,
            timer: 2500
          });
        }
      }
    );
  }
  ngOnInit(): void {
    this.newReservationFormGroup = this.fb.group({
      appartementReference: [this.appartementRef],
      client: this.fb.control(''),
      prixMetreCarre: this.fb.control(''),
    });
  }

}
