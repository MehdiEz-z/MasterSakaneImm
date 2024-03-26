import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../../shared/shared.module";
import {CommonModule, NgOptimizedImage} from "@angular/common";
import { ListResidencesComponent } from './list-residences/list-residences.component';
import { DetailsResidenceComponent } from './details-residence/details-residence.component';

const routes: Routes = [
  {
    path: "",
    component: ListResidencesComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL','CLIENT'] }
  },
  {
    path: ":reference",
    component: DetailsResidenceComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL','CLIENT'] }
  },
];
@NgModule({
  declarations: [
    ListResidencesComponent,
    DetailsResidenceComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    SharedModule,
    NgOptimizedImage,
  ]
})
export class ResidencesModule {}
