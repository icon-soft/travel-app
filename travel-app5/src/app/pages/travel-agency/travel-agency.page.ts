import { Component, OnInit } from '@angular/core';
import { Ville } from 'src/app/models/ville';
import { Agency } from 'src/app/models/agency';
import { Projection } from 'src/app/models/projection';
import { ProjectionTravelId } from 'src/app/models/projection-travel-id';
import { ActivatedRoute } from '@angular/router';
import { AgencyService } from 'src/app/services/agency.service';
import { BusService } from 'src/app/services/bus.service';
import { TravelService } from 'src/app/services/travel.service';
import { NgForm } from '@angular/forms';
import { LoaderService } from 'src/app/services/loader.service';
import { VilleService } from 'src/app/services/ville.service';
import { ConstantsService } from 'src/app/services/constants.service';

@Component({
  selector: 'app-travel-agency',
  templateUrl: './travel-agency.page.html',
  styleUrls: ['./travel-agency.page.scss'],
})
export class TravelAgencyPage implements OnInit {

  public ville: Ville;
  public villeID: string;
  public agencys: Agency[] = [];
  // public agencys: Agency[] = [{ id: 0, name: '', creationDate: null, nbreBus: 0, ville: null ,description:'',photo:''}];
  title = "LES AGENCES";
  // public maxDate: string;
  // public searchLoansResult: Projection[] = [];
  // public isNoResult: boolean = true;
  // public isFormSubmitted: boolean = false;
  // public projectionTravelId: ProjectionTravelId;
  // public selectedTickets = [];
  // public currentProjection;
  // public columns: any;
  // public rows: any;
  // public selectedIndex = -1;
  name;

  host: string;

  constructor(private activatedRoute: ActivatedRoute,
    private agenceService: AgencyService,
    private busService: BusService,
    private ionLoader: LoaderService,
    private _constant: ConstantsService,
    private travelService: TravelService) {
    this.host = _constant.baseAppUrl;
  }

  ngOnInit() {
    this.villeID = this.activatedRoute.snapshot.paramMap.get('id');
    this.onGetAgence(this.villeID);
    this.name = this.activatedRoute.snapshot.paramMap.get('name');
  }

  onGetAgence(id) {
    // this.ionLoader.showLoader();
    this.agenceService.loadAgencysVille(id).subscribe(
      (result: Agency[]) => {
        this.agencys.push.apply(this.agencys, result);
        this.ionLoader.hideLoader();
      },
      error => {
        this.ionLoader.hideLoader();
      }
    );
  }

  agence;
  // onGetSearch(ag) {
  //   this.isNoResult = true;
  //   this.idAgence = ag.id;
  //   this.agence = ag;
  // }

  idAgence;
  // searchLoansByType(searchLoanForm: NgForm) {
  //   // this.ionLoader.showLoader();
  //   if (!searchLoanForm.valid) {
  //     window.alert('Error in the form');
  //     this.ionLoader.hideLoader();
  //   }
  //   // this.searchLoansResult = [];
  //   console.log("ddddd " + this.idAgence);
  //   this.agenceService.searchProjectionTravelByDateTravel(this.maxDate, this.idAgence).subscribe(
  //     (result: Projection[]) => {
  //       this.treatResult(result);
  //       this.ionLoader.hideLoader();
  //     },
  //     error => {
  //       this.ionLoader.hideLoader();
  //     }
  //   );
  //   this.isFormSubmitted = searchLoanForm.submitted;
  // }

  // searchTitle = '';
  // searchLoanForm1() {
  //   this.ionLoader.showLoader();
  //   this.agenceService.searchProjectionTravelByDateTravel(this.maxDate, this.idAgence).subscribe(
  //     (result: Projection[]) => {
  //       this.treatResult(result);
  //       this.ionLoader.hideLoader();
  //       this.rows = result;
  //     },
  //     error => {
  //       this.ionLoader.hideLoader();
  //     }
  //   );
  //   this.isFormSubmitted = true;
  // }

  // treatResult(result: Projection[]) {
  //   this.searchLoansResult=[];
  //   if (result && result != null) {
  //     this.searchLoansResult = result;
  //     this.isNoResult = false;
  //     this.ionLoader.hideLoader();
  //     return;
  //   }
  //   this.isNoResult = true;
  // }

  // onGetBus(id) {
  //   this.ionLoader.showLoader();
  //   console.log("ggg " + id);
  //   this.busService.loadBusAgencys(id).
  //     subscribe((result: ProjectionTravelId) => {
  //       this.projectionTravelId = result;
  //       this.ionLoader.hideLoader();
  //     }, err => {
  //       this.ionLoader.hideLoader();
  //     })
  // }

  // onSelectTicket(t) {
  //   if (!t.selected) {
  //     t.selected = true;
  //     this.selectedTickets.push(t);
  //   } else {
  //     t.selected = false;
  //     this.selectedTickets.splice(this.selectedTickets.indexOf(t), 1);
  //   }

  // }

  // getTicketClass(t) {
  //   let str = "btn ticket ";
  //   if (t.reserve == true) {
  //     str += "btn-default";
  //   } else if (t.selected) {
  //     str += "btn-warning";
  //   } else {
  //     str += "btn-success";
  //   }
  //   return str;
  // }


  getImageName(film) {
    let imageName = film.titre.replace(/[ \\*-]/g, "");
    return imageName + ".jpg";
  }

}
