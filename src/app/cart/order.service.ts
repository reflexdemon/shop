import { Injectable } from '@angular/core';

import { Headers, Http,  Response } from '@angular/http';

import { Observable } from 'rxjs';

import { Order } from './order';

@Injectable()
export class OrderService {
private orderURL:string = '/rest/order';
  constructor(private http: Http)  { }


 placOrder():Observable<Order> {
   return this.http.post(this.orderURL,null)
   .map((r: Response) => r.json() as Order)
   .catch(this.handleError.bind(this));
 }



  private handleError(error: any): Observable<any> {
      console.log('An error occurred', error); // for demo purposes only
      return Observable.throw(error.json().message || 'Server error');
  }
}
