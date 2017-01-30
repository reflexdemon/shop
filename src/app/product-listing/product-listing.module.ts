import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterialModule } from '@angular/material';
import 'hammerjs';

import { CommonModule } from '@angular/common';

import { ProductListingComponent } from './product-listing.component';
import  { ProductListingService } from './product-listing.service';

@NgModule({
  imports: [
    BrowserModule,
   FormsModule,
    HttpModule,
    CommonModule,
    MaterialModule
  ],
  declarations: [
    ProductListingComponent
  ],
 providers: [
   ProductListingService
 ]
})
export class ProductListingModule { }
