import { Injectable } from '@angular/core';
import { Headers, Http,  Response } from '@angular/http';

import { Observable } from 'rxjs';

import { SearchResponse } from './search-response';
import { Product } from './product';


@Injectable()
export class ProductListingService {
  private catalogURL:string = '/rest/catalog';
  constructor(private http: Http)  { }

  findAll(page:number):Observable<SearchResponse> {
    return this.http.get(this.catalogURL + '/product?page=' + page)
           .map((r: Response) => r.json() as SearchResponse)
           .catch(this.handleError.bind(this));
  }

  findById(id: string):Observable<Product> {
    return this.http.get(this.catalogURL + '/product/' + id)
           .map((r: Response) => r.json() as Product)
           .catch(this.handleError.bind(this));
  }

  findByKeyword(keyword: string, page:number):Observable<SearchResponse> {
    return this.http.get(this.catalogURL + '/search?keyword=' + keyword + '&page='+page)
           .map((r: Response) => r.json() as SearchResponse)
           .catch(this.handleError.bind(this));
  }
  findByCategory(category: string):Observable<SearchResponse> {
    return this.http.get(this.catalogURL + '/category/' + category)
           .map((r: Response) => r.json() as SearchResponse)
           .catch(this.handleError.bind(this));
  }


  findCategories():Observable<Array<string>> {
    return this.http.get(this.catalogURL + '/category' )
           .map((r: Response) => r.json() as Array<string>)
           .catch(this.handleError.bind(this));
  }

  private handleError(error: any): Observable<any> {
      console.log('An error occurred', error); // for demo purposes only
      return Observable.throw(error.json().message || 'Server error');
  }
}
