import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ImmeubleService} from "../../../core/services/appartement/immeuble.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-add-immeuble',
  templateUrl: './add-immeuble.component.html',
  styleUrl: './add-immeuble.component.css'
})
export class AddImmeubleComponent implements OnInit{
  newImmeubleFormGroup!: FormGroup;
  residenceRef!: string;
  constructor(private route: ActivatedRoute,
              private immeubleService: ImmeubleService,
              public keycloakService: KeycloakService,
              private router: Router,
              private fb: FormBuilder) {
    this.residenceRef = this.route.snapshot.params['residenceReference'];
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  goBackResidenceDetail(): void {
    this.router.navigateByUrl("residences/"+this.residenceRef).then(
      r => console.log("Navigation vers la page de détail de la résidence")
    )
  }
  saveImmeuble() {
    let immeuble = this.newImmeubleFormGroup.value;
    this.immeubleService.saveImmeuble(immeuble).subscribe({
        next: (response : any) => {
          console.log(response);
          Swal.fire({
            icon: "success",
            title: response.message,
            showConfirmButton: false,
            timer: 2500
          }).then(r => {
            this.router.navigateByUrl("/immeubles/" + response.data.reference).then(r  =>
              console.log("navigate to immeuble " + response.data.reference));
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
    this.newImmeubleFormGroup = this.fb.group({
      residence: [this.residenceRef],
      numero: this.fb.control('')
    });
  }
}
