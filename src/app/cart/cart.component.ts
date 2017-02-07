import { Component, OnInit } from '@angular/core';

import { MdDialog, MdDialogRef, MdSnackBar } from '@angular/material';

import { CartService } from './cart.service';
import { Cart } from './cart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  cart:Cart;
  constructor (
    private cartService:CartService,
    private snackbar:MdSnackBar
  ) { }


  ngOnInit() {
    this.loadCart();
  }

  loadCart():void {
    this.cartService.getMyCart()
    .subscribe(
      data => {
        this.cart = data;
      },
      error => {
        this.snackbar.open(error, "Internal Error", {duration: 30000});
      }
    )    
  }

}
