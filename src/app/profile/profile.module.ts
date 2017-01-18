import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterialModule } from '@angular/material';
import 'hammerjs';

import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile.component';
@NgModule({
  imports: [
    BrowserModule,
   FormsModule,
    HttpModule,
    CommonModule,
    MaterialModule
  ],
  declarations: [
    ProfileComponent
  ],

})
export class ProfileModule { }
