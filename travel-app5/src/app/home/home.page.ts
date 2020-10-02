import { Component, ViewChild, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AgeValidator } from '../validators/age';
import { UsernameValidator } from '../validators/username';
import { LoaderService } from '../services/loader.service';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit, OnDestroy {


  constructor(private ionLoader: LoaderService, private router: Router, public loadingCtrl: LoadingController) {
    // this.showHideAutoLoader();
  }
  ngOnDestroy(): void {

  }
  ngOnInit(): void {

  }

  onLoadTravel() {
    this.router.navigate(['/travel'])
    // this.doSomething().then(truthy => { console.log('finished'); });
  }

  doSomething() {
    return new Promise((resolve, reject) => {
      //pretend a long-running task
      setTimeout(() => { resolve(true); }, 5000);
    });
  }

  ionViewWillEnter() {
    console.log("ionViewWillEnter")
    // for (let i = 0; i < 100000; i++) {
    //   console.log(i);
    // }
  }
  ionViewDidEnter() {
    console.log("ionViewDidEnter")
  }

  async showHideAutoLoader() {

    const loading = await this.loadingCtrl.create({
      message: 'Please wait...',
      duration: 7000
    });
    await loading.present();

    const { role, data } = await loading.onDidDismiss();
    // console.log('Loading dismissed! after 2 Seconds', { role, data });
    this.onLoadTravel();
  }
}