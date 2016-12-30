import {Component, Optional} from '@angular/core';
import {MdDialog, MdDialogRef, MdSnackBar} from '@angular/material';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Angular Webapp';
  isDarkTheme: boolean = false;

  constructor() { }

  ngOnInit() {
  }
}
