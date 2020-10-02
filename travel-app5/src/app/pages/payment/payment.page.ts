import { Component, OnInit } from '@angular/core';
import { NavParams, ModalController, AlertController } from '@ionic/angular';
import { TravelService } from 'src/app/services/travel.service';
import { BusService } from 'src/app/services/bus.service';
import { ProjectionTravelId } from 'src/app/models/projection-travel-id';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TicketForm } from 'src/app/models/ticket-form';
import { LoaderService } from 'src/app/services/loader.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.page.html',
  styleUrls: ['./payment.page.scss'],
})
export class PaymentPage implements OnInit {

  public selectedTickets = [];
  player: any;
  public myForm: FormGroup;

  constructor(navParams: NavParams, public modalController: ModalController,
    private travelService: TravelService,
    private busService: BusService,
    private _fb: FormBuilder,
    private ionLoader: LoaderService,
    private alertCtrl: AlertController) {
    this.selectedTickets = navParams.get('selectedTickets')
  }

  ngOnInit() {
    this.initPay();
  }

  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  cniPattern = "^((\\+91-?)|0)?[0-9]{9}$";
  initPay() {
    this.myForm = this._fb.group({
      cni: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(9), Validators.pattern(this.cniPattern)]],
      name: ['', [Validators.required, Validators.minLength(3)]],
      tel: ['', [Validators.required, Validators.minLength(9), Validators.pattern(this.cniPattern)]],
      codePayement: [],
      email: []
    });

  }

  dismiss() {
    this.modalController.dismiss();
  }

  private ticketForm = new TicketForm();
  public idTravel;
  onPayTickets() {
    this.ionLoader.showLoader();
    this.ticketForm = new TicketForm();
    this.ticketForm.cni = this.myForm.get('cni').value;
    this.ticketForm.codePayement = this.myForm.get('codePayement').value;
    this.ticketForm.email = this.myForm.get('email').value;
    this.ticketForm.name = this.myForm.get('name').value;
    this.ticketForm.tel = this.myForm.get('tel').value;

    let tickets = [];
    this.selectedTickets.forEach(t => {
      tickets.push(t.id);
    });
    this.ticketForm.tickets = tickets;

    console.log("this.currentProjection " + this.selectedTickets[0]);
    this.travelService.payerTickets(this.ticketForm).
      subscribe(
        data => {
          this.onGetBus(this.idTravel);
          this.selectedTickets = [];
          this.presentAlert();
          this.myForm.reset();
          this.ionLoader.hideLoader();
        }, err => {
          console.log(err);
          this.ionLoader.hideLoader();
        })
  }

  public projectionTravelId: ProjectionTravelId;
  onGetBus(id) {
    this.ionLoader.showLoader();
    this.idTravel = id;
    this.busService.loadBusAgencys(id).
      subscribe((result: ProjectionTravelId) => {
        this.projectionTravelId = result;
        this.ionLoader.hideLoader();
      }, err => {
        console.log(err);
        this.ionLoader.hideLoader();
      })
  }


  async presentAlert() {
    const alert = await this.alertCtrl.create({
      header: 'Reservation place',
      message: 'Place reservée avec succès !!!',
      buttons: ['OK']
    });
    await alert.present();
  }

}
