import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterialModule } from '@angular/material';
import 'hammerjs';
import { AppComponent} from './app.component';
import { AppRoutingModule } from './router/routing.module';
import { HomeComponent } from './home/home.component';
import { LostComponent, DialogContent } from './lost/lost.component';

@NgModule({
  declarations: [
    AppComponent,
    DialogContent,
    HomeComponent,
    LostComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MaterialModule.forRoot(),
    AppRoutingModule
  ],
  entryComponents: [DialogContent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
