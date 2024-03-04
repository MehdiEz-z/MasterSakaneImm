import { Component } from '@angular/core';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent {
  constructor(public sharedService: SharedService) { }
}
