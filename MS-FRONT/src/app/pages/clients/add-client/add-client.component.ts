import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ClientService} from "../../../core/services/client/client.service";
import {KeycloakService} from "keycloak-angular";
import {Router} from "@angular/router";
import Swal from "sweetalert2";
import {MethodeRecherche, SituationFamiliale} from "../../../core/models/client/enums";

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrl: './add-client.component.css'
})
export class AddClientComponent implements OnInit{
  newClientFormGroup!: FormGroup;
  situationsFamiliales = Object.keys(SituationFamiliale);
  methodesRecherche = Object.keys(MethodeRecherche);
  constructor(private clientService: ClientService,
              public keycloakService: KeycloakService,
              private router: Router,
              private fb: FormBuilder) {
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  saveClient() {
    let client = this.newClientFormGroup.value;
    client.dateNaissance = this.formatDate(client.dateNaissance);
    client.cinValidite = this.formatDate(client.cinValidite);
    this.clientService.saveClient(client).subscribe({
        next: (response : any) => {
          Swal.fire({
            icon: "success",
            title: response.message,
            showConfirmButton: false,
            timer: 2500
          }).then(r => {
            this.router.navigateByUrl("/clients/"+response.data.reference).then(
              r => console.log("navigate to client " + response.data.reference)
            );
          })
        },
        error: (error: any) => {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: error.error.message,
            showConfirmButton: false,
            timer: 2500
          });
        }
      }
    );
  }
  formatDate(date: string): string {
    const parts = date.split('-');
    return `${parts[2]}-${parts[1]}-${parts[0]}`;
  }
  ngOnInit(): void {
    this.newClientFormGroup = this.fb.group({
      nom: this.fb.control(''),
      prenom: this.fb.control(''),
      dateNaissance: this.fb.control(''),
      lieuNaissance: this.fb.control(''),
      nomPere: this.fb.control(''),
      nomMere: this.fb.control(''),
      adresse: this.fb.control(''),
      cin: this.fb.control(''),
      cinValidite: this.fb.control(''),
      telephone: this.fb.control(''),
      nationalite: this.fb.control(''),
      email: this.fb.control(''),
      profession: this.fb.control(''),
      situationFamiliale: this.fb.control(''),
      methode: this.fb.control(''),
    });
  }
}
