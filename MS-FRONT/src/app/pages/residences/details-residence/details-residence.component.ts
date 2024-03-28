import {Component, OnInit} from '@angular/core';
import {ResidenceService} from "../../../core/services/appartement/residence.service";
import {Residence} from "../../../core/models/appartement/residence";
import {ActivatedRoute, Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {Immeuble} from "../../../core/models/appartement/immeuble";
import {ImmeubleService} from "../../../core/services/appartement/immeuble.service";
import {Observable, switchMap, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import Swal from "sweetalert2";

@Component({
  selector: 'app-details-residence',
  templateUrl: './details-residence.component.html',
  styleUrl: './details-residence.component.css'
})
export class DetailsResidenceComponent implements OnInit {
  reference!: string;
  residence!: Observable<Residence>;
  errorMessage!: string;
  immeubles!: Observable<Immeuble[]>;

  constructor(private residenceService: ResidenceService,
              private route: ActivatedRoute,
              private immeubleService: ImmeubleService,
              public keycloakService: KeycloakService,
              private router: Router) {
    this.reference = this.route.snapshot.params['reference'];
  }

  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  getImmeubleDetails(immeuble: Immeuble) {
    this.router.navigateByUrl("immeubles/" + immeuble.reference)
  }
  getCreateImmeuble() {
    this.router.navigateByUrl("immeubles/add/" + this.reference)
  }
  ngOnInit(): void {
   this.residence = this.residenceService.getResidence(this.reference);
   this.immeubles = this.immeubleService.getImmeubles(this.reference).pipe(
     catchError((error) => {
          if (error.status == 404 && error.error.message) {
            this.errorMessage = error.error.message;
          }
          else if(error.status == 500){
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Une erreur interne du serveur est survenue lors de la récupération des immeubles.',
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
/*
ngOnInit(): void {
  this.residenceService.getResidence(this.reference).subscribe(residence => {
    this.residence = residence;
    if (this.residence) {
      this.immeubleService.getImmeubles(this.residence.reference).subscribe(immeubles => {
        this.immeubles = immeubles;
      });
    }
  });
}*/
