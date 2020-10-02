import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { VilleService } from './ville.service';

@Injectable({
  providedIn: 'root'
})
export class TravelService {

  constructor(private http: HttpClient, private hostService: VilleService) { }

  getTicketsPlaces(p) {
    let url = p._links.tickets.href.replace("{?projection}", "");
    return this.http.get(url + "?projection=ticketProj");
  }

  payerTickets(dataForm) {
    return this.http.post(this.hostService.host + "/payerTickets", dataForm);
  }
}
