import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../../shared/shared.module";
import {DetailsAppartementComponent} from "./details-appartement/details-appartement.component";
import { AddAppartementComponent } from './add-appartement/add-appartement.component';
import {ReactiveFormsModule} from "@angular/forms";

const routes: Routes = [
  {
    path: ":appartementReference",
    component: DetailsAppartementComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL','CLIENT'] }
  },
  {
    path: "add/:etageReference",
    component: AddAppartementComponent,
    data: { roles: ['ADMIN'] }
  }
];
@NgModule({
  declarations: [
    DetailsAppartementComponent,
    AddAppartementComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    SharedModule,
    ReactiveFormsModule,
  ]
})
export class AppartementModule { }
