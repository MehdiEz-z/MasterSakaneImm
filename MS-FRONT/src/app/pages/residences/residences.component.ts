import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-residences',
  templateUrl: './residences.component.html',
  styleUrl: './residences.component.css'
})
export class ResidencesComponent implements OnInit{
  constructor(private http: HttpClient) { }
  ngOnInit(): void {
  }

}
