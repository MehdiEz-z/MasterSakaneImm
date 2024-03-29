import {Component, OnInit} from '@angular/core';
import {Observable, throwError} from "rxjs";
import {Client} from "../../../core/models/client/client";
import {ClientService} from "../../../core/services/client/client.service";
import {KeycloakService} from "keycloak-angular";
import {catchError} from "rxjs/operators";
import Swal from "sweetalert2";

@Component({
  selector: 'app-list-clients',
  templateUrl: './list-clients.component.html',
  styleUrl: './list-clients.component.css'
})
export class ListClientsComponent implements OnInit{
  clients!: Observable<Client[]>;
  errorMessage!: string;
  constructor(private clientService: ClientService, public keycloakService: KeycloakService) {}
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  ngOnInit(): void {
    this.clients = this.clientService.getClients().pipe(
      catchError((error) => {
        if (error.status == 404 && error.error.message) {
          this.errorMessage = error.error.message;
        }
        else if(error.status == 500){
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Une erreur interne du serveur est survenue lors de la récupération des clients.',
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
