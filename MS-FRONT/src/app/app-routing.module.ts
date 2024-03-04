import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {ResidencesComponent} from "./pages/residences/residences.component";
import {ClientsComponent} from "./pages/clients/clients.component";
import {ReservationsComponent} from "./pages/reservations/reservations.component";

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'residences', component: ResidencesComponent },
  { path: 'clients', component: ClientsComponent },
  { path: 'reservations', component: ReservationsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
