import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { TravelAgencyPageRoutingModule } from './travel-agency-routing.module';

import { TravelAgencyPage } from './travel-agency.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    TravelAgencyPageRoutingModule
  ],
  declarations: [TravelAgencyPage]
})
export class TravelAgencyPageModule {}
