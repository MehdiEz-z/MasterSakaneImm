import {Component, OnInit} from '@angular/core';
import {SharedService} from "../../core/services/shared.service";
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{
  public profile! : KeycloakProfile;
  constructor(private sharedService: SharedService, public keycloakService : KeycloakService) {}
  addToggle(): void{
    this.sharedService.toggleSidebar();
  }
  ngOnInit(): void {
    if (this.keycloakService.isLoggedIn()) {
      this.keycloakService.loadUserProfile().then(profile => {
        this.profile = profile;
      });
    }
  }
}
