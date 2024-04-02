import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../../shared/shared.module";
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { ListReservationComponent } from './list-reservation/list-reservation.component';
import { DetailsReservationComponent } from './details-reservation/details-reservation.component';

const routes: Routes = [
  {
    path: "",
    component: ListReservationComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL'] }
  },
  {
    path: "add/:appartementReference",
    component: AddReservationComponent,
    data: { roles: ['ADMIN','COMMERCIAL'] }
  },
  {
    path: ":reference",
    component: DetailsReservationComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL'] }
  },
];
@NgModule({
  declarations: [
    AddReservationComponent,
    ListReservationComponent,
    DetailsReservationComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    SharedModule,
    NgOptimizedImage,
    ReactiveFormsModule,
  ]
})
export class ReservationModule {}
