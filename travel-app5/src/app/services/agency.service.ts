import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { VilleService } from './ville.service';
import { Observable } from 'rxjs';
import { Projection } from '../models/projection';

@Injectable({
  providedIn: 'root'
})
export class AgencyService {

  constructor(private http: HttpClient,
    private hostService:VilleService) { }

  loadAgencysVille(villeID:number) {
    return this.http.get(this.hostService.host+ '/allAgencyByVille?villeID='+villeID);
  }

  searchProjectionTravelByDateTravel(dateTravel: string, agencyID: number): Observable<Projection[]> {
    // let month: string = dateTravel.getMonth() < 10 ? '0' + (dateTravel.getMonth() + 1) : '' + (dateTravel.getMonth() + 1);
    // let dayOfMonth: string = dateTravel.getDate() < 10 ? '0' + dateTravel.getDate() : '' + dateTravel.getDate();
    // let dateTravelStr: string = dateTravel.getFullYear() + '-' + month + '-' + dayOfMonth;
    return this.http.get<Projection[]>(this.hostService.host + '/projectionByDateTravelAndAgency?dateProjectionTravel=' + dateTravel + '&&agencyID=' + agencyID);
  }
}
