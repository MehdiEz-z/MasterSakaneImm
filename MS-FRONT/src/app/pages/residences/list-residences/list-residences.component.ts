import { Component, OnInit } from '@angular/core';
import { Residence } from "../../../core/models/appartement/residence";
import { ResidenceService } from "../../../core/services/appartement/residence.service";
import { KeycloakService } from "keycloak-angular";
import Swal from 'sweetalert2';
import { Observable, throwError } from "rxjs";
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-list-residences',
  templateUrl: './list-residences.component.html',
  styleUrls: ['./list-residences.component.css']
})
export class ListResidencesComponent implements OnInit {
  residences!: Observable<Residence[]>;
  errorMessage!: string;

  constructor(private residenceService: ResidenceService, public keycloakService: KeycloakService) { }

  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }

  ngOnInit(): void {
    this.residences = this.residenceService.getResidences().pipe(
      catchError((error) => {
        if (error.status == 404 && error.error.message) {
          this.errorMessage = error.error.message;
        }
        else if(error.status == 500){
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Une erreur interne du serveur est survenue lors de la récupération des résidences. Veuillez réessayer plus tard.',
            showConfirmButton: false,
            timer: 4500
          }).then(r => {
            setTimeout(() => {
              window.location.reload();
            }, 5000);
          });
        }
        return throwError(error);
      })
    );
  }
}
