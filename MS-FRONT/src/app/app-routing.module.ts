import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {ResidencesComponent} from "./pages/residences/residences.component";
import {ClientsComponent} from "./pages/clients/clients.component";
import {ReservationsComponent} from "./pages/reservations/reservations.component";
import {AuthGuard} from "./core/guards/auth.guard";

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate : [AuthGuard], data: { roles: ['ADMIN', 'COMMERCIAL'] } },
  { path: 'residences', component: ResidencesComponent, canActivate : [AuthGuard], data: { roles: ['ADMIN', 'COMMERCIAL'] } },
  { path: 'clients', component: ClientsComponent, canActivate : [AuthGuard], data: { roles: ['ADMIN', 'COMMERCIAL'] } },
  { path: 'reservations', component: ReservationsComponent, canActivate : [AuthGuard], data: { roles: ['ADMIN', 'COMMERCIAL'] } },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
