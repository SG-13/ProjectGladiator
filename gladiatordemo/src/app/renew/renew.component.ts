import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-renew',
  templateUrl: './renew.component.html',
  styleUrls: ['./renew.component.css']
})
export class RenewComponent implements OnInit {

  public show:boolean = false;
  public first:boolean = true;
  public showm:boolean = false;
  Duration:any[]=['1 year','2 year','3 year','4 year']
 


  myFunc()
  {
    this.first=false;
this.show= true;

  }
  myFunc2()
  {
    this.showm=true;
this.show= false;

  }


  ngOnInit(): void {
  }

}
