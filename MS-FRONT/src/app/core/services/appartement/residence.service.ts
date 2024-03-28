import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environement/environement";
import {Residence} from "../../models/appartement/residence";
import {map} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class ResidenceService {

  private apiUrl = environment.apiApt;
  constructor(private http: HttpClient) { }
  getResidences() {
    return this.http.get<{ data: Residence[] }>(`${this.apiUrl}/residences/all`)
      .pipe(
        map(response => response.data)
      );
  }
  getResidence(reference: string) {
    return this.http.get<{ data: Residence }>(`${this.apiUrl}/residences/${reference}`)
      .pipe(
        map(response => response.data)
      );
  }
  saveResidence(residence: Residence) {
    return this.http.post<Residence>(`${this.apiUrl}/residences/`, residence)
      .pipe(
        map(response => response)
      );
  }
}
