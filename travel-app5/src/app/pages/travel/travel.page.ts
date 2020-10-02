import { Component, OnInit } from '@angular/core';
import { Ville } from 'src/app/models/ville';
import { Platform, LoadingController } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { VilleService } from 'src/app/services/ville.service';
import { LoaderService } from 'src/app/services/loader.service';

@Component({
  selector: 'app-travel',
  templateUrl: './travel.page.html',
  styleUrls: ['./travel.page.scss'],
})
export class TravelPage implements OnInit {

  public selectedIndex = 0;
  public villes: Ville[] = [];

  constructor(
    private ionLoader: LoaderService,
    private villeService: VilleService,
  ) {
  }



  ngOnInit() {
    const path = window.location.pathname.split('folder/')[1];
    if (path !== undefined) {
      // this.selectedIndex = this.appPages.findIndex(page => page.title.toLowerCase() === path.toLowerCase());
    }
    this.getVilles();
  }


  // showLoader() {
  //   this.ionLoader.showLoader();

  //   setTimeout(() => {
  //     this.hideLoader();
  //   }, 2000);
  // }

  // hideLoader() {
  //   this.ionLoader.hideLoader();
  // }

  async getVilles() {
    await this.ionLoader.showLoading('ifOfLoading')
    this.villeService.loadVilles()
      .subscribe(
        (result: Ville[]) => {
          this.villes.push.apply(this.villes, result);
          this.ionLoader.dismissLoader('ifOfLoading')
        }, err => {
          console.log(err);
          this.ionLoader.dismissLoader('ifOfLoading')
        });
  }
}
