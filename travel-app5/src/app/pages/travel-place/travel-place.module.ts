import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, FormBuilder, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { TravelPlacePageRoutingModule } from './travel-place-routing.module';

import { TravelPlacePage } from './travel-place.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    IonicModule,
    TravelPlacePageRoutingModule
  ],
  declarations: [TravelPlacePage]
})
export class TravelPlacePageModule {}
