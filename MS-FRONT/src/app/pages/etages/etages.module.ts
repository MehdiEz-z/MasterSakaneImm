import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetailsEtageComponent } from './details-etage/details-etage.component';
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../../shared/shared.module";
import { AddEtageComponent } from './add-etage/add-etage.component';
import {ReactiveFormsModule} from "@angular/forms";

const routes: Routes = [
  {
    path: ":etageReference",
    component: DetailsEtageComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL','CLIENT'] }
  },
  {
    path: "add/:immeubleReference",
    component: AddEtageComponent,
    data: { roles: ['ADMIN'] }
  }
];
@NgModule({
  declarations: [
    DetailsEtageComponent,
    AddEtageComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    SharedModule,
    ReactiveFormsModule,
  ]
})
export class EtagesModule { }
