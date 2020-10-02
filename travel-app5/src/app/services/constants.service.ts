import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConstantsService {

  // readonly baseAppUrl: string = 'http://localhost:7000';
  readonly  baseAppUrl: string = "http://192.168.8.100:7000";
  readonly distLocation: string = 'MyApplication/';
}
