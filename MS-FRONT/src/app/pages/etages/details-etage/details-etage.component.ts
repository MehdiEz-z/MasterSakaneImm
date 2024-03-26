import {Component, OnInit} from '@angular/core';
import {Etage} from "../../../core/models/appartement/etage";
import {ActivatedRoute, Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {EtageService} from "../../../core/services/appartement/etage.service";
import {Appartement} from "../../../core/models/appartement/appartement";
import {AppartementService} from "../../../core/services/appartement/appartement.service";

@Component({
  selector: 'app-details-etage',
  templateUrl: './details-etage.component.html',
  styleUrl: './details-etage.component.css'
})
export class DetailsEtageComponent implements OnInit{
  etageRef: string | undefined;
  etage: Etage | undefined;
  appartements: Appartement[] = [];

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
    this.router.navigateByUrl("appartements/detail/"+ appartement.reference);
  }
  goBack(): void {
    window.history.back();
  }
  ngOnInit(): void {
    this.etageService.getEtage(this.etageRef).subscribe(etage => {
      this.etage = etage;
      if(this.etage){
        this.appartementService.getAppartements(this.etage.reference).subscribe(appartements => {
          this.appartements = appartements;
        })
      }
    });
  }
}
