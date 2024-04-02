import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
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
  {
    path: "clients",
    loadChildren: () =>
      import("./pages/clients/client.module").then((m  => m.ClientModule))
  },
  {
    path: 'reservations',
    loadChildren: () =>
      import("./pages/reservations/reservation.module").then((m  => m.ReservationModule))
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
