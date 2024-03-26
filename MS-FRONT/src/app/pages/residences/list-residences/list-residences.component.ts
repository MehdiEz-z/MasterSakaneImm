import {Component, OnInit} from '@angular/core';
import {Residence} from "../../../core/models/appartement/residence";
import {ResidenceService} from "../../../core/services/appartement/residence.service";
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-list-residences',
  templateUrl: './list-residences.component.html',
  styleUrl: './list-residences.component.css'
})
export class ListResidencesComponent implements OnInit{
  residences: Residence[] = [];
  constructor(private residenceService: ResidenceService,
              public keycloakService: KeycloakService) { }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  ngOnInit(): void {
    this.residenceService.getResidences().subscribe(residences => {
      this.residences = residences;
    });
  }
}
