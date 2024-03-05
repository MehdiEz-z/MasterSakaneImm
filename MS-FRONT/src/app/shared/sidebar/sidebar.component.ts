import { Component } from '@angular/core';
import {SharedService} from "../../core/services/shared.service";
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  constructor(private sharedService: SharedService, public keycloakService: KeycloakService) {}
  get isSidebarHidden(): boolean {
    return this.sharedService.getSidebarStatus();
  }
  handleLogout() {
    this.keycloakService.logout(window.location.origin);
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
}
