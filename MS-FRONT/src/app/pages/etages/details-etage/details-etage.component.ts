import {Component, OnInit} from '@angular/core';
import {Etage} from "../../../core/models/appartement/etage";
import {ActivatedRoute, Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {EtageService} from "../../../core/services/appartement/etage.service";
import {Appartement} from "../../../core/models/appartement/appartement";
import {AppartementService} from "../../../core/services/appartement/appartement.service";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import Swal from "sweetalert2";

@Component({
  selector: 'app-details-etage',
  templateUrl: './details-etage.component.html',
  styleUrl: './details-etage.component.css'
})
export class DetailsEtageComponent implements OnInit{
  etageRef!: string;
  etage!: Observable<Etage>;
  appartements!: Observable<Appartement[]>;
  errorMessage!: string;

  constructor(private route: ActivatedRoute,
              private etageService: EtageService,
              private appartementService: AppartementService,
              public keycloakService: KeycloakService,
              private router: Router) {
    this.etageRef = this.route.snapshot.params['etageReference'];
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  getAppartementDetails(appartement: Appartement){
    this.router.navigateByUrl("appartements/"+ appartement.reference).then(
      r => console.log("Navigation vers la page de détail de l'appartement")
    );
  }
  getCreateAppartement(){
    this.router.navigateByUrl("appartements/add/"+this.etageRef).then(
      r => console.log("Navigation vers la page de création d'un appartement")
    );
  }
  goBackImmeubleDetail(): void {
    this.router.navigateByUrl("immeubles/"+(this.etageRef)?.slice(0,11)).then(
      r => console.log("Navigation vers la page de détail d'immeuble")
    )
  }
  ngOnInit(): void {
    this.etage = this.etageService.getEtage(this.etageRef);
    this.appartements = this.appartementService.getAppartements(this.etageRef).pipe(
      catchError((error) => {
        if (error.status == 404 && error.error.message) {
          this.errorMessage = error.error.message;
        }
        else if(error.status == 500){
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Une erreur interne du serveur est survenue lors de la récupération des appartements.',
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
