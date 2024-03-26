import { Injectable } from '@angular/core';
import {environment} from "../../../../environement/environement";
import {HttpClient} from "@angular/common/http";
import {Etage} from "../../models/appartement/etage";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EtageService{
  private apiUrl = environment.apiApt;
  constructor(private http: HttpClient) { }
  getEtages(reference: string | undefined){
    return this.http.get<{data : Etage[]}>(`${this.apiUrl}/etages/${reference}/all`)
      .pipe(
        map(response => response.data)
      );
  }
  getEtage(referenceEtage: string | undefined){
    return this.http.get<{data : Etage}>(`${this.apiUrl}/etages/${referenceEtage}`)
      .pipe(
        map(response => response.data)
      );
  }
}
