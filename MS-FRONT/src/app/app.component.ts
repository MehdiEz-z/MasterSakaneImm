import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent  implements OnInit{
  isAuthenticated: boolean = false;
  constructor(private keycloakService: KeycloakService) {}

  ngOnInit(): void {
    this.isAuthenticated = this.keycloakService.isLoggedIn();
  }
  async handleLogin() {
    await this.keycloakService.login({
      redirectUri: window.location.origin + '/dashboard'
    });
  }
}
