import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../../shared/shared.module";
import {CommonModule} from "@angular/common";
import { DetailsImmeubleComponent } from './details-immeuble/details-immeuble.component';

const routes: Routes = [
  {
    path: "detail/:immeubleReference",
    component: DetailsImmeubleComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL','CLIENT'] }
  }
];
@NgModule({
  declarations: [
    DetailsImmeubleComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    SharedModule,
  ]
})
export class ImmeublesModule {}
