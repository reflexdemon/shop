import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterialModule } from '@angular/material';
import 'hammerjs';

import { CommonModule } from '@angular/common';

import { ProductListingComponent, AddToCartDialog } from './product-listing.component';
import { ProductListingService } from './product-listing.service';

import { SharedModule } from '../shared/shared.module'

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CommonModule,
    MaterialModule,
    SharedModule
  ],
  declarations: [
    ProductListingComponent,
    AddToCartDialog
  ],
  providers: [
    ProductListingService
  ],
  entryComponents: [
    AddToCartDialog
  ]
})
export class ProductListingModule { }
