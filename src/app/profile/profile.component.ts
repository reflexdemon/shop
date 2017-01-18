import { Component, OnInit } from '@angular/core';

import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user: User;
  constructor(userService: UserService) {
    userService.getUser().subscribe(
      data => {
      console.log("User:", data);
        return this.user = data;
      },
      error => {
        console.log("Problem!!!", error);
      }
    );
  }

  ngOnInit() {
  }

}
