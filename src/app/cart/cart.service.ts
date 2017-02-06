import { Injectable } from '@angular/core';

import { Headers, Http,  Response } from '@angular/http';

import { Observable } from 'rxjs';

import { Product } from '../product-listing/product';
import { CartRequest } from './cart-request';
import { Cart } from './cart';

@Injectable()
export class CartService {
private cartURL:string = '/rest/cart';
  constructor(private http: Http)  { }

  //TODO: Need to build the Cart Object
  getMyCart():Observable<Cart> {
    return this.http.get(this.cartURL)
           .map((r: Response) => r.json() as Cart)
           .catch(this.handleError.bind(this));
  }

//TODO: Need to build the Cart Object
 addToCart(request: CartRequest):Observable<Cart> {
   return this.http.post(this.cartURL, request)
   .map((r: Response) => r.json() as Cart)
   .catch(this.handleError.bind(this));
 }


  private handleError(error: any): Observable<any> {
      console.log('An error occurred', error); // for demo purposes only
      return Observable.throw(error.json().message || 'Server error');
  }
}
