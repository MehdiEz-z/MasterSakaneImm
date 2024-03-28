import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../../shared/shared.module";
import {DetailsAppartementComponent} from "./details-appartement/details-appartement.component";

const routes: Routes = [
  {
    path: ":appartementReference",
    component: DetailsAppartementComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL','CLIENT'] }
  }
];
@NgModule({
  declarations: [
    DetailsAppartementComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    SharedModule,
  ]
})
export class AppartementModule { }
