import { NgModule }              from '@angular/core';
import { RouterModule, Routes, RouterLink }  from '@angular/router';

import { HomeComponent }     from './../home/home.component';
import { LostComponent } from './../lost/lost.component';
import { LoginComponent } from './../login/login.component';
import { PageComponent } from './../page/page.component';
import { SignUpComponent } from '../sign-up/sign-up.component';
import { ProfileComponent } from '../profile/profile.component';
import { ProductListingComponent } from '../product-listing/product-listing.component';
import {  CartComponent } from '../cart/cart.component';

const appRoutes: Routes = [
  { path: '',   component: HomeComponent },
  { path: 'page', component: PageComponent },
  { path: 'loginpage', component: LoginComponent },
  { path: 'signup', component: SignUpComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'products', component: ProductListingComponent },
  { path: 'search/:keyword', component: ProductListingComponent },
  { path: 'cart', component: CartComponent },
  { path: '**', component: LostComponent }
];
@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, { useHash: true })

  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {}
