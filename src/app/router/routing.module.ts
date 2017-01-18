import { NgModule }              from '@angular/core';
import { RouterModule, Routes, RouterLink }  from '@angular/router';

import { HomeComponent }     from './../home/home.component';
import { LostComponent } from './../lost/lost.component';
import { LoginComponent } from './../login/login.component';
import { PageComponent } from './../page/page.component';
import { SignUpComponent } from '../sign-up/sign-up.component';
import { ProfileComponent } from '../profile/profile.component';

const appRoutes: Routes = [
  { path: '',   component: HomeComponent },
  { path: 'page', component: PageComponent },
  { path: 'loginpage', component: LoginComponent },
  { path: 'signup', component: SignUpComponent },
  { path: 'profile', component: ProfileComponent },
  { path: '**', component: LostComponent }
];
@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, { useHash: false })

  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {}
