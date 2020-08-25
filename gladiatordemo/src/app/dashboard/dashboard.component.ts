import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
show=false;
showb=false;
  constructor() { }

  ngOnInit(): void {
  }
  showdiv(){
    this.show=true;
    this.showb=false;
  }
  showdivb(){
    this.showb=true;
    this.show=false;
  }

}
