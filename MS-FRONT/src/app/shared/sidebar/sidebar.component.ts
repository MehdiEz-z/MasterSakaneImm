import { Component } from '@angular/core';
import {SharedService} from "../../core/service/shared.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  constructor(private sharedService: SharedService) {}
  get isSidebarHidden(): boolean {
    return this.sharedService.getSidebarStatus();
  }
}
