import {Component, OnInit} from '@angular/core';
import {ResidenceService} from "../../../core/services/appartement/residence.service";
import {KeycloakService} from "keycloak-angular";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Residence} from "../../../core/models/appartement/residence";
import Swal from "sweetalert2";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-residence',
  templateUrl: './add-residence.component.html',
  styleUrl: './add-residence.component.css'
})
export class AddResidenceComponent implements OnInit{
  newResidenceFormGroup!: FormGroup;
  constructor(private residenceService: ResidenceService,
              public keycloakService: KeycloakService,
              private router: Router,
              private fb: FormBuilder) {
  }

  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }

  saveResidence() {
    let residence:Residence = this.newResidenceFormGroup.value;
    this.residenceService.saveResidence(residence).subscribe({
        next: (response : any) => {
          console.log(response);
          Swal.fire({
            icon: "success",
            title: response.message,
            showConfirmButton: false,
            timer: 2500
          }).then(r => {
            this.router.navigateByUrl("/residences/"+response.data.reference);
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
  ngOnInit(): void {
    this.newResidenceFormGroup = this.fb.group({
      nom: this.fb.control(''),
      adresse: this.fb.control(''),
      description: this.fb.control('')
    });
  }
}
