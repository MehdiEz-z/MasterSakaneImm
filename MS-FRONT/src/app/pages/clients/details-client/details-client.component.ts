import {Component, OnInit} from '@angular/core';
import {Observable, throwError} from "rxjs";
import {Client} from "../../../core/models/client/client";
import {ClientService} from "../../../core/services/client/client.service";
import {ActivatedRoute, Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {catchError} from "rxjs/operators";
import Swal from "sweetalert2";

@Component({
  selector: 'app-details-client',
  templateUrl: './details-client.component.html',
  styleUrl: './details-client.component.css'
})
export class DetailsClientComponent implements OnInit{
  reference!: string;
  client!: Observable<Client>;
  errorMessage!: string;
  constructor(private clientService: ClientService,
              private route: ActivatedRoute,
              private router: Router,
              public keycloakService: KeycloakService) {
    this.reference = this.route.snapshot.params['clientReference'];
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  ngOnInit(): void {
    this.client = this.clientService.getClient(this.reference).pipe(
      catchError((error) => {
        if (error.status == 404 && error.error.message) {
          this.errorMessage = error.error.message;
        }
        else if(error.status == 500){
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Une erreur interne du serveur est survenue lors de la récupération du client.',
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
