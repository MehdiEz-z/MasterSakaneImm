import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../../shared/shared.module";
import {CommonModule} from "@angular/common";
import { DetailsImmeubleComponent } from './details-immeuble/details-immeuble.component';
import { AddImmeubleComponent } from './add-immeuble/add-immeuble.component';
import {ReactiveFormsModule} from "@angular/forms";

const routes: Routes = [
  {
    path: ":immeubleReference",
    component: DetailsImmeubleComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL','CLIENT'] }
  },
  {
    path: "add/:residenceReference",
    component: AddImmeubleComponent,
    data: { roles: ['ADMIN'] }
  }
];
@NgModule({
  declarations: [
    DetailsImmeubleComponent,
    AddImmeubleComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    SharedModule,
    ReactiveFormsModule,
  ]
})
export class ImmeublesModule {}
