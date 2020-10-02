import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then( m => m.HomePageModule)
  },
  {
    path: '',
    redirectTo: 'travel',
    pathMatch: 'full'
  },
  {
    path: 'travel',
    loadChildren: () => import('./pages/travel/travel.module').then( m => m.TravelPageModule)
  },
  {
    path: 'travel-detail/:id/:name',
    loadChildren: () => import('./pages/travel-detail/travel-detail.module').then( m => m.TravelDetailPageModule)
  },
  {
    path: 'travel-agency/:id/:name',
    loadChildren: () => import('./pages/travel-agency/travel-agency.module').then( m => m.TravelAgencyPageModule)
  },
  {
    path: 'travel-place/:id',
    loadChildren: () => import('./pages/travel-place/travel-place.module').then( m => m.TravelPlacePageModule)
  },
  {
    path: 'payment',
    loadChildren: () => import('./pages/payment/payment.module').then( m => m.PaymentPageModule)
  },
  {
    path: 'test',
    loadChildren: () => import('./pages/test/test.module').then( m => m.TestPageModule)
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
