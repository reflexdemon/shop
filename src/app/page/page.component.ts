import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'redirect',
  template: 'redirecting...'
})
export class PageComponent implements OnInit {
  constructor() {

  }

  ngOnInit() {
    this.gotoAPI();
  }

  gotoAPI() {
    console.log('Click to API received.', window.location)
    window.location.href =  "/swagger-ui.html";
  }

}
