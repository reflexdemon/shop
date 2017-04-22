import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterialModule } from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MdButtonModule, MdCheckboxModule, MdInputContainer, MdInputModule} from '@angular/material';

import 'hammerjs';

import { AppComponent} from './app.component';
import { AppRoutingModule } from './router/routing.module';
import { HomeComponent } from './home/home.component';
import { LostComponent, DialogContent } from './lost/lost.component';
import { PageComponent } from './page/page.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UserService  } from './user.service';
import { User  } from './user';
import { ProfileModule } from './profile/profile.module';
import { ProductListingModule } from './product-listing/product-listing.module';
import { CartModule} from './cart/cart.module';


@NgModule({
  declarations: [
    AppComponent,
    DialogContent,
    HomeComponent,
    LostComponent,
    PageComponent,
    LoginComponent,
    SignUpComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MaterialModule.forRoot(),
    BrowserAnimationsModule,
    AppRoutingModule,
    ProfileModule,
    ProductListingModule,
    CartModule
  ],
  entryComponents: [
    DialogContent
  ],
  providers: [
    UserService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
