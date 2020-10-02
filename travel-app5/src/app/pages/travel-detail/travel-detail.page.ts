import { Component, OnInit } from '@angular/core';
import { Projection } from 'src/app/models/projection';
import { ProjectionTravelId } from 'src/app/models/projection-travel-id';
import { ActivatedRoute } from '@angular/router';
import { ModalController, AlertController } from '@ionic/angular';
import { AgencyService } from 'src/app/services/agency.service';
import { BusService } from 'src/app/services/bus.service';
import { TravelService } from 'src/app/services/travel.service';
import { LoaderService } from 'src/app/services/loader.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-travel-detail',
  templateUrl: './travel-detail.page.html',
  styleUrls: ['./travel-detail.page.scss'],
})
export class TravelDetailPage implements OnInit {

  public isFormSubmitted: boolean = false;
  private isNoResult: boolean = false;
  public maxDate: Date;
  public searchTraveResult: Projection[] = [];
  public projectionTravelId: ProjectionTravelId;
  public selectedIndex = -1;
  idAgence;

  constructor(private activatedRoute: ActivatedRoute,
    private agenceService: AgencyService,
    private busService: BusService,
    public modalController: ModalController,
    private ionLoader: LoaderService,
    public datepipe: DatePipe,
    private alertCtrl: AlertController,
    private travelService: TravelService) { }

  name;

  ngOnInit() {
    this.name = this.activatedRoute.snapshot.paramMap.get('name');
    let agenceid = this.activatedRoute.snapshot.paramMap.get('id');
    this.idAgence = agenceid;

    this.dateJour = this.datepipe.transform(new Date(), 'yyyy-MM-dd');
    this.searchLoanForm1(this.dateJour);
    this.myDate = this.dateJour
  }
  date;

  dateJour: string;
  myDate: string;

  dateSelect($event) {
    this.myDate = this.datepipe.transform(new Date($event), 'yyyy-MM-dd');
    this.searchLoanForm1(this.myDate);
    // console.log("this.searchTraveResul "+this.searchTraveResult.length);
    console.log("5 this.isNoResult " + this.isNoResult);
    if (!this.isNoResult) {
      this.presentAlert();
    }
  }

  async presentAlert() {
    const alert = await this.alertCtrl.create({
      header: 'Search travel',
      message: 'Aucun voyage trouvé à cette date !!!',
      buttons: ['OK']
    });
    await alert.present();
  }

  searchLoanForm1(date) {
    // await this.ionLoader.showLoading('ifOfLoading')
    this.agenceService.searchProjectionTravelByDateTravel(date, this.idAgence).subscribe(
      (result: Projection[]) => {
        this.treatResult(result);
        console.log("1 result " + result.length);
        if (result.length > 0) {
          console.log("2 Taille sup " + result.length);
          this.isNoResult = true;
        } else {
          this.isNoResult = false;
          console.log("3 Taille inf " + result.length);
          console.log("4 val isNoResult " + this.isNoResult);
        }
        // await this.ionLoader.dismissLoader('ifOfLoading')
      },
      error => {
        this.ionLoader.dismissLoader('ifOfLoading')
      }
    );
  }

  treatResult(result: Projection[]) {
    this.searchTraveResult = [];
    if (result && result != null) {
      this.searchTraveResult = result;
      // this.isNoResult = true;
      // this.ionLoader.dismissLoader('ifOfLoading')
      return;
    }
    // this.isNoResult = false;
  }

  async onGetBus(id) {
    await this.ionLoader.showLoading('ifOfLoading');
    this.busService.loadBusAgencys(id).
      subscribe((result: ProjectionTravelId) => {
        this.projectionTravelId = result;
        this.ionLoader.dismissLoader('ifOfLoading')
      }, err => {
        this.ionLoader.dismissLoader('ifOfLoading')
      })
  }

}
