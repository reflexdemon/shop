import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterialModule } from '@angular/material';
import 'hammerjs';

import { CommonModule } from '@angular/common';

import { SharedComponent } from './shared.component';


@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    SharedComponent
  ]
})
export class SharedModule { }
