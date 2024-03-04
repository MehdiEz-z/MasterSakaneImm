import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedModule} from "../shared/shared.module";
import { DashboardComponent } from './dashboard/dashboard.component';
import { ResidencesComponent } from './residences/residences.component';
import {RouterLink} from "@angular/router";
import { ClientsComponent } from './clients/clients.component';
import { ReservationsComponent } from './reservations/reservations.component';

@NgModule({
  declarations: [
    DashboardComponent,
    ResidencesComponent,
    ClientsComponent,
    ReservationsComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterLink
  ]
})
export class PagesModule { }
