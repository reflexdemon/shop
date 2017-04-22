import { Component, Optional, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef, MdSnackBar } from '@angular/material';
import { Router, ActivatedRoute, Params, NavigationEnd } from '@angular/router';

import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/filter';



import { ProductListingService } from './product-listing.service';
import { CartService } from '../cart/cart.service';

import { CartRequest } from '../cart/cart-request';
import { SearchResponse } from './search-response';
import { Product } from './product';



@Component({
  selector: 'app-product-listing',
  templateUrl: './product-listing.component.html',
  styleUrls: ['./product-listing.component.scss']
})
export class ProductListingComponent implements OnInit {

  result: SearchResponse;
  cacheResult: SearchResponse;
  error: string;
  totalPages: number;
  pages: number[];
  fetched: boolean;
  keyword: string;
  imageWidth: number = 200;
  imageMargin: number = 5;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productListingService: ProductListingService,
    private cartService: CartService,
    private _dialog: MdDialog
  ) {

  }

  getPages() {
    if (this.result) {
      let totalPages = Math.ceil(this.result.maxCount / this.result.limit);
      this.totalPages = totalPages;
      this.pages = [];
      for (let i = 1; i <= this.totalPages; i++) {
        this.pages.push(i);
      }
    }
  }

  showPage(page: number) {
    this.getProducts(page, this.keyword);
  }

  ngOnInit() {
    this.router.events
      .filter(event => event instanceof NavigationEnd)
      .subscribe((event: NavigationEnd) => {
        // console.log('Router Changed:NavigationEnd', event);
        this.updateResults();
      });
  }

  updateResults(): void {
    this.keyword = this.route.snapshot.params['keyword'];
    this.getProducts(1, this.keyword);
  }

  getProducts(page: number, keyword: string) {
    this.result = null;
    this.error = null;
    this.fetched = false;

    if (keyword) {
      this.productListingService.findByKeyword(keyword, page)
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

  onData(data: any) {
    console.log('Data:', data);
    this.result = data;
    this.error = null;
    this.getPages();
    this.fetched = true;
  }

  onError(error: any) {
    this.result = null;
    this.error = error;
    this.fetched = true;
  }
  addToCart(product: Product, quantity: number) {
    console.log("Item to Add:", product);
    this.cacheResult = this.result;
    this.result = null;
    let request: CartRequest = { "productId": product.id, "quantity": quantity };
    this.cartService.addToCart(request)
      .subscribe(
      data => {
        console.log("Item successfully added to the cart");
        this.result = this.cacheResult;
        this.openDialog();
      },
      error => {
        this.result = this.cacheResult;
        console.log("Problem while adding item to the cart");
      }
      )
  }

  openDialog() {
    let dialogRef = this._dialog.open(AddToCartDialog);

    dialogRef.afterClosed().subscribe(response => {
      console.log("Response:", response);
      if (response) {
        this.router.navigate(["/cart"]);
      }
    })
  }
}




@Component({
  templateUrl: './add-to-cart-dialog.html'
})
export class AddToCartDialog {
  constructor( @Optional() public dialogRef: MdDialogRef<AddToCartDialog>) { }
}