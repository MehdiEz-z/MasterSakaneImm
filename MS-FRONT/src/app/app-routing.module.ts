import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {ClientsComponent} from "./pages/clients/clients.component";
import {ReservationsComponent} from "./pages/reservations/reservations.component";
import {AuthGuard} from "./core/guards/auth.guard";

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate : [AuthGuard], data: { roles: ['ADMIN', 'COMMERCIAL'] } },
  {
    path: "residences",
    loadChildren: () =>
      import("./pages/residences/residences.module").then((m  => m.ResidencesModule))
  },
  {
    path: "immeubles",
    loadChildren: () =>
      import("./pages/immeubles/immeubles.module").then((m  => m.ImmeublesModule))
  },
  {
    path: "etages",
    loadChildren: () =>
      import("./pages/etages/etages.module").then((m  => m.EtagesModule))
  },
  {
    path: "appartements",
    loadChildren: () =>
      import("./pages/appartements/appartement.module").then((m  => m.AppartementModule))
  },
  { path: 'clients', component: ClientsComponent, canActivate : [AuthGuard], data: { roles: ['ADMIN', 'COMMERCIAL'] } },
  { path: 'reservations', component: ReservationsComponent, canActivate : [AuthGuard], data: { roles: ['ADMIN', 'COMMERCIAL','CLIENT'] } },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
