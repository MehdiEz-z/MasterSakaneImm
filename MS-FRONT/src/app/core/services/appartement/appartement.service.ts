import { Injectable } from '@angular/core';
import {environment} from "../../../../environement/environement";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";
import {Appartement} from "../../models/appartement/appartement";
@Injectable({
  providedIn: 'root'
})
export class AppartementService {
  private apiUrl = environment.apiApt;
  constructor(private http: HttpClient) { }

  getAppartements(reference: string | undefined){
    return this.http.get<{data : Appartement[]}>(`${this.apiUrl}/appartements/${reference}/all`)
      .pipe(
        map(response => response.data)
      );
  }
  getAppartement(referenceAppartement: string | undefined){
    return this.http.get<Appartement>(`${this.apiUrl}/appartements/${referenceAppartement}`)
      .pipe(
        map(response => response)
      );
  }
  saveAppartement(appartement: Appartement) {
    return this.http.post<Appartement>(`${this.apiUrl}/appartements/`, appartement)
      .pipe(
        map(response => response)
      );
  }
}
