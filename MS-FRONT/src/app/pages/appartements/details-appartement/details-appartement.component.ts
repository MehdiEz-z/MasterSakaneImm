import {Component, OnInit} from '@angular/core';
import {Appartement} from "../../../core/models/appartement/appartement";
import {ActivatedRoute, Router} from "@angular/router";
import {AppartementService} from "../../../core/services/appartement/appartement.service";
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-details-appartement',
  templateUrl: './details-appartement.component.html',
  styleUrl: './details-appartement.component.css'
})
export class DetailsAppartementComponent implements OnInit{
  appartementRef: string | undefined;
  appartement: Appartement | undefined;
  constructor(private route: ActivatedRoute,
              private appartementService: AppartementService,
              public keycloakService: KeycloakService,
              private router: Router) {
    this.appartementRef = this.route.snapshot.params['appartementReference'];
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  goBack(): void {
    window.history.back();
  }
  ngOnInit(): void {
    this.appartementService.getAppartement(this.appartementRef).subscribe(appartement => {
      this.appartement = appartement;
    });
  }

}
