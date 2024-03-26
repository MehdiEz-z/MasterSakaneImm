import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetailsEtageComponent } from './details-etage/details-etage.component';
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../../shared/shared.module";

const routes: Routes = [
  {
    path: "detail/:etageReference",
    component: DetailsEtageComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL','CLIENT'] }
  }
];
@NgModule({
  declarations: [
    DetailsEtageComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    SharedModule,
  ]
})
export class EtagesModule { }
