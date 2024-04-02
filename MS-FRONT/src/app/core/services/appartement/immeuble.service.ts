import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environement/environement";
import {Immeuble} from "../../models/appartement/immeuble";
import {map} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class ImmeubleService {

  private apiUrl = environment.apiApt;
  constructor(private http: HttpClient) { }

  getImmeubles(reference: string){
    return this.http.get<{data : Immeuble[]}>(`${this.apiUrl}/immeubles/${reference}/all`)
      .pipe(
        map(response => response.data)
      );
  }
  getImmeuble(referenceImmeuble: string | undefined){
    return this.http.get<{data : Immeuble}>(`${this.apiUrl}/immeubles/${referenceImmeuble}`)
      .pipe(
        map(response => response.data)
      );
  }
  saveImmeuble(immeuble: Immeuble) {
    return this.http.post<Immeuble>(`${this.apiUrl}/immeubles/`, immeuble)
      .pipe(
        map(response => response)
      );
  }
}
