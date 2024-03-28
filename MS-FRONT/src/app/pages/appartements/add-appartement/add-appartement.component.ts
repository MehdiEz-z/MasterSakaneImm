import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {AppartementService} from "../../../core/services/appartement/appartement.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-add-appartement',
  templateUrl: './add-appartement.component.html',
  styleUrl: './add-appartement.component.css'
})
export class AddAppartementComponent implements OnInit{
  newAppartementFormGroup!: FormGroup;
  etageRef!: string;
  constructor(private route: ActivatedRoute,
              private appartementService: AppartementService,
              public keycloakService: KeycloakService,
              private router: Router,
              private fb: FormBuilder) {
    this.etageRef = this.route.snapshot.params['etageReference'];
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  goBackEtageDetail(): void {
    this.router.navigateByUrl("etages/"+this.etageRef).then(
      r => console.log("Navigation vers la page de détail de l'étage")
    )
  }
  saveAppartement() {
    let appartement = this.newAppartementFormGroup.value;
    this.appartementService.saveAppartement(appartement).subscribe({
        next: (response : any) => {
          console.log(response);
          Swal.fire({
            icon: "success",
            title: response.message,
            showConfirmButton: false,
            timer: 2500
          }).then(r => {
            this.router.navigateByUrl("/appartements/" + response.data.reference).then(r  =>
              console.log("navigate to appartement " + response.data.reference));
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
    this.newAppartementFormGroup = this.fb.group({
      etage: [this.etageRef],
      numero: this.fb.control(''),
      description: this.fb.control(''),
      titreFoncier: this.fb.control(''),
      superficieUtile: this.fb.control(''),
      superficieTerrasse: this.fb.control(''),
      prixMetreCarre: this.fb.control(''),
      nombreChambre: this.fb.control(''),
      nombreSalon: this.fb.control(''),
      nombreCuisine: this.fb.control(''),
      nombreSalleDeBain: this.fb.control(''),
      nombrePlacard: this.fb.control(''),
      nombreBalcon: this.fb.control(''),
    });
  }

}
