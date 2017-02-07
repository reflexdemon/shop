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

  getMyCart():Observable<Cart> {
    return this.http.get(this.cartURL)
           .map((r: Response) => r.json() as Cart)
           .catch(this.handleError.bind(this));
  }

 addToCart(request: CartRequest):Observable<Cart> {
   return this.http.post(this.cartURL, request)
   .map((r: Response) => r.json() as Cart)
   .catch(this.handleError.bind(this));
 }

updateQuantity(request: CartRequest):Observable<Cart> {
   return this.http.put(this.cartURL + '/lineItem', request)
   .map((r: Response) => r.json() as Cart)
   .catch(this.handleError.bind(this));
 }

 delete(productId: string):Observable<Cart> {
   return this.http.delete(this.cartURL + '/lineItem/' + productId)
   .map((r: Response) => r.json() as Cart)
   .catch(this.handleError.bind(this));
 }

  private handleError(error: any): Observable<any> {
      console.log('An error occurred', error); // for demo purposes only
      return Observable.throw(error.json().message || 'Server error');
  }
}
