import {Component, OnInit} from '@angular/core';
import {ResidenceService} from "../../../core/services/appartement/residence.service";
import {Residence} from "../../../core/models/appartement/residence";
import {ActivatedRoute, Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {Immeuble} from "../../../core/models/appartement/immeuble";
import {ImmeubleService} from "../../../core/services/appartement/immeuble.service";

@Component({
  selector: 'app-details-residence',
  templateUrl: './details-residence.component.html',
  styleUrl: './details-residence.component.css'
})
export class DetailsResidenceComponent implements OnInit{
  reference: string | undefined;
  residence: Residence | undefined;
  immeubles: Immeuble[] = [];
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
  getImmeubleDetails(immeuble : Immeuble){
    this.router.navigateByUrl("immeubles/detail/"+ immeuble.reference)
  }
  ngOnInit(): void {
    this.residenceService.getResidence(this.reference).subscribe(residence => {
      this.residence = residence;
      if (this.residence) {
        this.immeubleService.getImmeubles(this.residence.reference).subscribe(immeubles => {
          this.immeubles = immeubles;
        });
      }
    });
  }
}
