import { Component, Optional } from '@angular/core';
import { MdDialog, MdDialogRef, MdSnackBar } from '@angular/material';

import { UserService } from './user.service';
import { User } from './user';


@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    title = 'Shop';
    isDarkTheme: boolean = false;
    user: User = {
      id: "58659560561d906dcb4567b3",
      firstName: "Venkateswara",
      lastName: "Venkatraman Prasanna",
      username: "venkatvp"};
    constructor(userService: UserService) {
        userService.getUser().subscribe(
          data => {
          console.log("User:", data);
          return this.user = data;
          },
          error => {
            console.log('Problem!!!', error);
          }
        );
    }

    ngOnInit() {
    }
}
