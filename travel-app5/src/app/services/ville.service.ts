import { Injectable } from '@angular/core';
import { ConstantsService } from './constants.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ville } from '../models/ville';

@Injectable({
  providedIn: 'root'
})
export class VilleService {

  host: string;
  constructor(private http: HttpClient, private _constant: ConstantsService) {
    this.host = _constant.baseAppUrl;
  }

  loadVilles(): Observable<Ville[]> {
    let headers = new HttpHeaders();
    headers.append('content-type', 'application/json');
    headers.append('accept', 'application/json');
    return this.http.get<Ville[]>(this.host + '/allVilles', { headers: headers });
  }

}
