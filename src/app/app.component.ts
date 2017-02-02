import { Component, Optional } from '@angular/core';
import { MdDialog, MdDialogRef, MdSnackBar } from '@angular/material';

import { UserService } from './user.service';
import { User } from './user';
import { Router } from '@angular/router';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    title = 'Shop';
    isDarkTheme: boolean = false;
    user: User = {"id":"9439842e-8fb3-42d5-af5c-b0f74cc74c0c","firstName":"anonymous","lastName":"anonymous","organization":null,"email":null,"phone":null,"mobile":null,"pricingTag":null,"username":"anonymousUser","password":null,"authorities":null,"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true};
    dummy: User = {"id":"9439842e-8fb3-42d5-af5c-b0f74cc74c0c","firstName":"anonymous","lastName":"anonymous","organization":null,"email":null,"phone":null,"mobile":null,"pricingTag":null,"username":"anonymousUser","password":null,"authorities":null,"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true};
    constructor(
      private router:Router,
      userService: UserService
      , private snackbar:MdSnackBar
    ) {
        userService.getUser().subscribe(
          data => {
          console.log("User:", data);
            return this.user = data;
          },
          error => {
            console.log("Problem!!!", error);
            return this.dummy;
          }
        );
    }

    isAnonymous():boolean {
      return this.user.firstName === "anonymous";
    }

    ngOnInit() {
    }

    showMessage(message:string, brief:string):void {
      this.snackbar.open(message, brief, {duration: 30000});
    }

    searchWith(keyword:string) {
      console.log("keyword:", keyword);
      if (!this.isAnonymous()) {
        this.router.navigate(['/search', keyword]);
      } else {
        this.showMessage("Please Sign In before searching.", "Login Required");
      }

    }
}
