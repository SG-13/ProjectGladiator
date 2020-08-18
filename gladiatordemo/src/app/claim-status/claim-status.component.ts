import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-claim-status',
  templateUrl: './claim-status.component.html',
  styleUrls: ['../app.component.css']
})
export class ClaimStatusComponent implements OnInit {

  showt:boolean=false;
  first:boolean=true;

  showtable(){
    this.first=false;
    this.showt=true;
  }
  constructor() { }

  ngOnInit(): void {
  }



}
