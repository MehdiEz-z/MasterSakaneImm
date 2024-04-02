import { Injectable } from '@angular/core';
import {environment} from "../../../../environement/environement";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";
import {Reservation} from "../../models/reservation/reservation";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private apiUrl = environment.apiRsv;

  constructor(private http: HttpClient) { }
  getReservations() {
    return this.http.get<{ data: Reservation[] }>(`${this.apiUrl}/reservations/all`)
      .pipe(
        map(response => response.data)
      );
  }
  getReservation(reference: string) {
    return this.http.get<{data: Reservation }>(`${this.apiUrl}/reservations/${reference}`)
      .pipe(
        map(response => response.data)
      );
  }
  saveReservation(reservation: Reservation) {
    return this.http.post<Reservation>(`${this.apiUrl}/reservations/`, reservation)
      .pipe(
        map(response => response)
      );
  }
  annulerReservation(reference: string) {
    return this.http.put(`${this.apiUrl}/reservations/${reference}/annuler`,null)
      .pipe(
        map(response => response)
      );
  }
  confirmerReservation(reference: string) {
    return this.http.put(`${this.apiUrl}/reservations/${reference}/confirmer`,null)
      .pipe(
        map(response => response)
      );
  }
}
