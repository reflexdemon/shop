import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'list-paging',
  templateUrl: './paging.component.html',
  styleUrls: ['./paging.component.scss']
})
export class PagingComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  showPage(page:any) {
    console.log("page:", page);
  }

}
