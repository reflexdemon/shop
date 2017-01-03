import { Component, OnInit } from '@angular/core';

import { UserService  } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  userService: UserService;
  constructor(userService: UserService) {
    this.userService = userService;
  }

  ngOnInit() {
  }

  login(username:string, password:string) {
    console.log('Login clicked!');
    // this.userService.login(username, password); 
  }
}
