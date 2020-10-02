import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TravelAgencyPage } from './travel-agency.page';

const routes: Routes = [
  {
    path: '',
    component: TravelAgencyPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TravelAgencyPageRoutingModule {}
