import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { ImmeubleService } from "../../../core/services/appartement/immeuble.service";
import { KeycloakService } from "keycloak-angular";
import { Immeuble } from "../../../core/models/appartement/immeuble";
import {EtageService} from "../../../core/services/appartement/etage.service";
import {Etage} from "../../../core/models/appartement/etage";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import Swal from "sweetalert2";

@Component({
  selector: 'app-details-immeuble',
  templateUrl: './details-immeuble.component.html',
  styleUrls: ['./details-immeuble.component.css']
})
export class DetailsImmeubleComponent implements OnInit {
  immeubleRef!: string;
  immeuble!: Observable<Immeuble>;
  errorMessage!: string;
  etages!: Observable<Etage[]>;

  constructor(private route: ActivatedRoute,
              private immeubleService: ImmeubleService,
              private etageService: EtageService,
              public keycloakService: KeycloakService,
              private router: Router) {
    this.immeubleRef = this.route.snapshot.params['immeubleReference'];
  }

  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role))
  }
  getEtageDetails(etage : Etage){
    this.router.navigateByUrl("etages/"+ etage.reference).then(
      r => console.log("Navigation vers la page de détail de l'étage")
    )
  }
  getCreateEtage(){
    this.router.navigateByUrl("etages/add/"+this.immeubleRef).then(
      r => console.log("Navigation vers la page de création d'un étage")
    )
  }
  goBackResidenceDetail(): void {
    this.router.navigateByUrl("residences/"+(this.immeubleRef)?.slice(0,5)).then(
      r => console.log("Navigation vers la page de détail de la résidence")
    )
  }
  ngOnInit(): void {
    this.immeuble = this.immeubleService.getImmeuble(this.immeubleRef);
    this.etages = this.etageService.getEtages(this.immeubleRef).pipe(
      catchError((error) => {
        if (error.status == 404 && error.error.message) {
          this.errorMessage = error.error.message;
        }
        else if(error.status == 500){
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Une erreur interne du serveur est survenue lors de la récupération des etages.',
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
