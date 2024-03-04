import { Component } from '@angular/core';
import {SharedService} from "../../core/service/shared.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  constructor(private sharedService: SharedService) {}
  addToggle(): void{
    this.sharedService.toggleSidebar();
  }
}
