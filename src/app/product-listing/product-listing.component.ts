import { Component, OnInit } from '@angular/core';

import  { ProductListingService } from './product-listing.service';
import { SearchResponse } from './search-response';
import { Router, ActivatedRoute, Params } from '@angular/router';

import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-product-listing',
  templateUrl: './product-listing.component.html',
  styleUrls: ['./product-listing.component.scss']
})
export class ProductListingComponent  implements OnInit {

  private result : SearchResponse;
  private error: string;
  totalPages:number;
  pages:number[];
  private keyword:string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productListingService:ProductListingService
  ) {

  }

  getPages() {
    if (this.result) {
      let totalPages = Math.ceil(this.result.maxCount / this.result.limit);
      this.totalPages = totalPages;
      this.pages = [];
      for (let i=1; i <= this.totalPages; i++) {
        this.pages.push(i);
      }
    }
  }

showPage(page:number) {
  console.log("Page:", page);
  this.getProducts(page, this.keyword);
}

  ngOnInit() {
    this.keyword = this.route.snapshot.params['keyword'];
    console.log("Keyword for searching:", this.keyword);
    this.getProducts(1, this.keyword);

  }


  getProducts(page: number, keyword:string) {
    this.result = null;

    if (keyword) {
      this.productListingService.findByKeyword(this.keyword, 0)
      .subscribe(
        this.onData.bind(this),
        this.onError.bind(this)
      );
    } else {
      this.productListingService.findAll(page)
      .subscribe(
        this.onData.bind(this),
        this.onError.bind(this)
      );
    }

  }

  onData(data:any) {
    this.result = data;
    this.getPages();
  }

  onError(error:any) {
    this.result = null;
    this.error = error;
  }

}
