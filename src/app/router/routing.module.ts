import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';

import { HomeComponent }     from './../home/home.component';
import { LostComponent } from './../lost/lost.component';

const appRoutes: Routes = [
  { path: '',   component: HomeComponent },
  { path: '**', component: LostComponent }
];
@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {}
