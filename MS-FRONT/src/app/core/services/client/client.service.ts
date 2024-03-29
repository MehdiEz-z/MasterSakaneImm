import { Injectable } from '@angular/core';
import {environment} from "../../../../environement/environement";
import {HttpClient} from "@angular/common/http";
import {Client} from "../../models/client/client";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl = environment.apiApt;
  constructor(private http: HttpClient) { }
  getClients() {
    return this.http.get<{ data: Client[] }>(`${this.apiUrl}/clients/all`)
      .pipe(
        map(response => response.data)
      );
  }
  getClient(reference: string) {
    return this.http.get<{ data: Client }>(`${this.apiUrl}/clients/${reference}`)
      .pipe(
        map(response => response.data)
      );
  }
}
