import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  sidebarStatus: boolean = false;
  constructor() { }
  toggleSidebar(): void {
    this.sidebarStatus = !this.sidebarStatus;
  }
  getSidebarStatus(): boolean {
    return this.sidebarStatus;
  }
}
