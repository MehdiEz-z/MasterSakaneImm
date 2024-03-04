import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SideBarComponent } from './side-bar/side-bar.component';
import { NavbarComponent } from './navbar/navbar.component';

@NgModule({
  declarations: [
    SideBarComponent,
    NavbarComponent
  ],
  exports: [
    SideBarComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule
  ]
})
export class SharedModule {}
