import { Component, OnInit, ViewChild } from '@angular/core';
import { Projection } from 'src/app/models/projection';
import { ProjectionTravelId } from 'src/app/models/projection-travel-id';
import { ActivatedRoute } from '@angular/router';
import { VilleService } from 'src/app/services/ville.service';
import { BusService } from 'src/app/services/bus.service';
import { AlertController, ModalController } from '@ionic/angular';
import { TravelService } from 'src/app/services/travel.service';
import { PaymentPage } from '../payment/payment.page';
import { LoaderService } from 'src/app/services/loader.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TicketForm } from 'src/app/models/ticket-form';

@Component({
  selector: 'app-travel-place',
  templateUrl: './travel-place.page.html',
  styleUrls: ['./travel-place.page.scss'],
})
export class TravelPlacePage implements OnInit {

  public searchLoansResult: Projection[] = [];
  public isNoResult: boolean = true;
  public isFormSubmitted: boolean = false;
  public projectionTravelId: ProjectionTravelId;
  public selectedTickets = [];
  public currentProjection;

  @ViewChild('signupSlider') signupSlider;
  public myForm: FormGroup;
  // projection: any;

  constructor(private activatedRoute: ActivatedRoute,
    private hostService: VilleService,
    private busService: BusService,
    private alertCtrl: AlertController,
    public modalController: ModalController,
    private ionLoader: LoaderService,
    private _fb: FormBuilder,
    private travelService: TravelService) {
    // this.projection = navParams.get('projection');
  }
  // ngDoCheck(): void {
  //   console.log("ngDoCheck")
  //   this.onGetBus(this.idTravel);
  // }
  // ngOnChanges(changes: SimpleChanges): void {
  //   console.log("ngOnChanges")
  //   this.onGetBus(this.idTravel);
  // }

  ngOnInit() {
    this.initPay();
  }

  idTravel;
  ionViewWillEnter() {
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this.idTravel = id;
    this.onGetBus(this.idTravel);
  }

  agence;
  onGetBus(id) {
    // this.ionLoader.showLoader();
    this.busService.loadBusAgencys(id).
      subscribe((result: ProjectionTravelId) => {
        this.projectionTravelId = result;
        this.ionLoader.hideLoader();
      }, err => {
        this.ionLoader.hideLoader();
      })
  }

  onSelectTicket(t) {
    this.selectedTickets = [];
    if (!t.selected) {
      t.selected = true;
      this.selectedTickets.push(t);
      // this.openModalPayment(this.selectedTickets);
      this.signupSlider.slideTo(1);
    } else {
      t.selected = false;
      this.selectedTickets.splice(this.selectedTickets.indexOf(t), 1);
    }
  }

  async openModalPayment(selectedTickets) {
    const modal = await this.modalController.create({
      component: PaymentPage,
      componentProps: { selectedTickets: this.selectedTickets }
    });
    this.onGetBus(this.idTravel);
    return await modal.present();
  }

  getTicketClass(t) {
    let str = "btn ticket ";
    if (t.reserve == true) {
      str += "btn-warning";
    } else if (t.selected) {
      str += "btn-info";
    } else {
      str += "btn-success btn-lg";
    }
    return str;
  }

  // PARTIE PAYEMENT
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

  private ticketForm = new TicketForm();
  async onPayTickets() {
    // this.ionLoader.showLoader();
    await this.ionLoader.showLoading('ifOfLoading')
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
          // this.ionLoader.hideLoader();
           this.ionLoader.dismissLoader('ifOfLoading');
          this.presentAlert();
          this.myForm.reset();
          this.signupSlider.slideTo(0);
          
        }, err => {
          console.log(err);
          this.ionLoader.dismissLoader('ifOfLoading');
          // this.ionLoader.hideLoader();
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
