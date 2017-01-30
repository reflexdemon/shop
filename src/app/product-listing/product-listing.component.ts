import { Component, OnInit } from '@angular/core';

import  { ProductListingService } from './product-listing.service';
import { SearchResponse } from './search-response';


@Component({
  selector: 'app-product-listing',
  templateUrl: './product-listing.component.html',
  styleUrls: ['./product-listing.component.scss']
})
export class ProductListingComponent implements OnInit {

  private result : SearchResponse;
  private error: string;
  totalPages:number;
  pages:number[];

  constructor(private productListingService:ProductListingService) {

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
  this.getProducts(page);
}

  ngOnInit() {
    this.getProducts(0);
  }

  getProducts(page: number) {
    this.productListingService.findAll(page)
    .subscribe(
      data => {
        this.result = data;
        this.getPages();
      },
      error => {
        this.result = null;
        this.error = error;
      }
    );
  }


}
