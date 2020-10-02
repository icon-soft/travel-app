import { Injectable } from '@angular/core';
import { LoadingController } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class LoaderService {

  loader: HTMLIonLoadingElement;
  isLoading = false;

  constructor(public loadingCtrl: LoadingController) { }

  async showHideAutoLoader() {

    const loading = await this.loadingCtrl.create({
      message: 'Please wait...',
      duration: 7000
    });
    await loading.present();

    const { role, data } = await loading.onDidDismiss();
    console.log('Loading dismissed! after 2 Seconds', { role, data });

  }

  // This will show then autohide the loader
  showHideAutoLoader1() {

    this.loadingCtrl.create({
      message: 'This Loader Will Auto Hide in 2 Seconds',
      duration: 2000
    }).then((res) => {
      res.present();

      res.onDidDismiss().then((dis) => {
        console.log('Loading dismissed! after 2 Seconds', dis);
      });
    });

  }

  async showLoader() {
    let loading = await this.loadingCtrl.create({
      message: 'Please wait...',
      spinner: 'circles'
    });
    await loading.present();
  }
  // Show the loader for infinite time
  showLoader1() {

    this.loadingCtrl.create({
      message: 'Please wait...'
    }).then((res) => {
      res.present();
    });

  }

  // Hide the loader if already created otherwise return error
  hideLoader() {

    this.loadingCtrl.dismiss().then((res) => {
      console.log('Loading dismissed!', res);
    }).catch((error) => {
      console.log('error', error);
    });

  }


  async loadingPresent() {
    this.isLoading = true;
    return await this.loadingCtrl.create({
      message: 'Please wait ...',
      spinner: 'circles'
    }).then(a => {
      a.present().then(() => {
        console.log('loading presented');
        if (!this.isLoading) {
          a.dismiss().then(() => console.log('abort laoding'));
        }
      });
    });
  }

  async loadingDismiss() {
    this.isLoading = false;
    return await this.loadingCtrl.dismiss().then(() => console.log('loading dismissed'));
  }

  async showLoading(loadingId: string, loadingMessage: string = 'Please wait ...') {
    const loading = await this.loadingCtrl.create({
      id: loadingId,
      message: loadingMessage,
      spinner: 'circles'
    });
    return await loading.present();
  }

  async dismissLoader(loadingId: string) {
    return await this.loadingCtrl.dismiss(null, null, loadingId).then(() => console.log('loading dismissed'));
  }

}
