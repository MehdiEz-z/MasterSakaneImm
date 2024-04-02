import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../../shared/shared.module";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ListClientsComponent } from './list-clients/list-clients.component';
import { DetailsClientComponent } from './details-client/details-client.component';
import {AddClientComponent} from "./add-client/add-client.component";

const routes: Routes = [
  {
    path: "",
    component: ListClientsComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL'] }
  },
  {
    path: "add",
    component: AddClientComponent,
    data: { roles: ['ADMIN','COMMERCIAL'] }
  },
  {
    path: ":clientReference",
    component: DetailsClientComponent,
    data: { roles: ['ADMIN', 'COMMERCIAL'] }
  }
];
@NgModule({
  declarations: [
    ListClientsComponent,
    DetailsClientComponent,
    AddClientComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
  ]
})
export class ClientModule {}
