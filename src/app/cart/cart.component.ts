import { Component, OnInit } from '@angular/core';

import { MdDialog, MdDialogRef, MdSnackBar } from '@angular/material';
import { Router, ActivatedRoute, Params, NavigationEnd } from '@angular/router';

import { CartService } from './cart.service';
import { OrderService } from './order.service';
import { Order } from './order';
import { Cart } from './cart';
import { LineItem  } from './line-item';
import { CartRequest  } from './cart-request';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  cart:Cart;
  constructor (
    private cartService:CartService,
    private orderService:OrderService,
    private snackbar:MdSnackBar,
    private route: ActivatedRoute,
    private router: Router
  ) { }


  ngOnInit() {
    this.loadCart();
  }

  loadCart():void {
    this.cartService.getMyCart()
    .subscribe(
      this.onData.bind(this),
      this.onError.bind(this)
    )
  }


  add(lineItem:LineItem) {
    let request:CartRequest = {productId : lineItem.productId, quantity: (lineItem.quantity + 1)};
    this.cart = null;
    this.cartService.updateQuantity(request)
    .subscribe(
      this.onData.bind(this),
      this.onError.bind(this)
    )
  }

  gotoProducts() {
    this.router.navigate(["/products"]);
  }

  remove(lineItem:LineItem) {
    let request:CartRequest = {productId : lineItem.productId, quantity: (lineItem.quantity - 1)};
    this.cart = null;
    this.cartService.updateQuantity(request)
    .subscribe(
      this.onData.bind(this),
      this.onError.bind(this)
    )
  }
  delete(lineItem:LineItem) {
    this.cart = null;
    this.cartService.delete(lineItem.productId)
    .subscribe(
      this.onData.bind(this),
      this.onError.bind(this)
    )
  }

  isEmptyCart():boolean {
    return ( this.cart && this.cart.lineItems && this.cart.lineItems.length <= 0); 
  }

  placeOrder():void {
    console.log("Place Order Called!");
    this.orderService.placOrder()
    .subscribe(
      this.handleOrder.bind(this),
      this.onError.bind(this)
    );
  }

  handleOrder(data:Order) {
    this.gotoProducts();
    this.snackbar.open(data.id, "Order Placed!", {duration: 30000});    
  }

  onData(data:Cart) {
      this.cart = data;
  }

  onError(error:string) {
    this.snackbar.open(error, "Internal Error", {duration: 30000});
  }

}
