import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import { MdSnackBar} from '@angular/material';

import { UserService } from '../user.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
// [SILVER_TAG, GOLD_TAG, BRONZE_TAG, PLATINUM_TAG]

  tags = [
      'PLATINUM_TAG',
      'GOLD_TAG',
      'SILVER_TAG',
      'BRONZE_TAG'
    ];

  constructor(private userService:UserService, private snackbar:MdSnackBar) { }

  ngOnInit() {
  }

  onSubmit(user:NgForm) {
    // console.log("Form:", user);
    // console.log("User Object:", user.value);
    // console.log("User Valid:", user.valid);
    if (user.valid) { //Ensure all required fields are filled.
      this.userService.createUser(user.value).subscribe(
        data => {
          console.log("User Creation successful", data);
          this.snackbar.open("Successfully user created", "Successful", {duration: 5000});
        },
        error => {
          this.snackbar.open(error, "User Error", {duration: 30000});
        }
      )
    } else {
      this.snackbar.open("Please ensure to fill all the mandatory fields.", "Invalid input!", {duration: 5000});
    }
  }
}
