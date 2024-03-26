import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { ImmeubleService } from "../../../core/services/appartement/immeuble.service";
import { KeycloakService } from "keycloak-angular";
import { Immeuble } from "../../../core/models/appartement/immeuble";
import {EtageService} from "../../../core/services/appartement/etage.service";
import {Etage} from "../../../core/models/appartement/etage";

@Component({
  selector: 'app-details-immeuble',
  templateUrl: './details-immeuble.component.html',
  styleUrls: ['./details-immeuble.component.css']
})
export class DetailsImmeubleComponent implements OnInit {
  immeubleRef: string | undefined;
  immeuble: Immeuble | undefined;
  etages: Etage[] = [];

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
    this.router.navigateByUrl("etages/detail/"+ etage.reference)
  }
  goBack(): void {
    window.history.back();
  }
  ngOnInit(): void {
    this.immeubleService.getImmeuble(this.immeubleRef).subscribe(immeuble => {
      this.immeuble = immeuble;
      if(this.immeuble){
        this.etageService.getEtages(this.immeuble.reference).subscribe(etages => {
          this.etages = etages;
        })
      }
    });
  }
}
