import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { VilleService } from './ville.service';
import { ProjectionTravelId } from '../models/projection-travel-id';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BusService {

  constructor(private http: HttpClient,private hostService:VilleService) { }

  loadBusAgencys(projectionTravelId: ProjectionTravelId): Observable<ProjectionTravelId> {
    let headers = new HttpHeaders();
    headers.append('content-type', 'application/json');
    headers.append('accept', 'application/json');
    return this.http.get<ProjectionTravelId>(this.hostService.host + '/projectionTravelByPk?projectionTravelId=' + projectionTravelId, { headers: headers });
  }
  
}
