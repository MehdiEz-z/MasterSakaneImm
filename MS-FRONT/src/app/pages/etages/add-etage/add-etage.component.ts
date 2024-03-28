import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";
import {EtageService} from "../../../core/services/appartement/etage.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-add-etage',
  templateUrl: './add-etage.component.html',
  styleUrl: './add-etage.component.css'
})
export class AddEtageComponent implements OnInit{
  newEtageFormGroup!: FormGroup;
  immeubleRef!: string;
  constructor(private route: ActivatedRoute,
              private etageService: EtageService,
              public keycloakService: KeycloakService,
              private router: Router,
              private fb: FormBuilder) {
    this.immeubleRef = this.route.snapshot.params['immeubleReference'];
  }
  hasAnyRoles(roles: string[]): boolean {
    const userRoles = this.keycloakService.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
  goBackImmeubleDetail(): void {
    this.router.navigateByUrl("immeubles/"+this.immeubleRef).then(
      r => console.log("Navigation vers la page de détail de la résidence")
    )
  }
  saveEtage() {
    let etage = this.newEtageFormGroup.value;
    this.etageService.saveEtage(etage).subscribe({
        next: (response : any) => {
          console.log(response);
          Swal.fire({
            icon: "success",
            title: response.message,
            showConfirmButton: false,
            timer: 2500
          }).then(r => {
            this.router.navigateByUrl("/etages/" + response.data.reference).then(r  =>
              console.log("navigate to etage " + response.data.reference));
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
    this.newEtageFormGroup = this.fb.group({
      immeuble: [this.immeubleRef],
      numero: this.fb.control('')
    });
  }

}
