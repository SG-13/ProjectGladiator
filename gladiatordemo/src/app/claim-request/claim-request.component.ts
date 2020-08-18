import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-claim-request',
  templateUrl: './claim-request.component.html',
  styleUrls: ['../app.component.css']
})
export class ClaimRequestComponent implements OnInit {

  first:boolean=true;
  show:boolean=false;
  showm:boolean=false;

  showmsg(){
    this.showm=true;
    this.show=false;
  }

  showform(){
    this.first=false;
    this.show=true;
  }

  constructor() { }

  ngOnInit(): void {
  }

}

