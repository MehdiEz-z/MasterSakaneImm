import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Master Sakane';
  status = false;
  addToggle()
  {
    this.status = !this.status;
  }
}
