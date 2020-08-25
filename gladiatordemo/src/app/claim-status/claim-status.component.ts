import { Component, OnInit } from '@angular/core';
import { List } from "../listl";
import { AngularServiceService } from "../angular-service.service";
import { User } from "../user";

@Component({
  selector: 'app-claim-status',
  templateUrl: './claim-status.component.html',
  styleUrls: ['./claim-status.component.css']
})
export class ClaimStatusComponent implements OnInit {

  list1=Array<List>();
  user:User=new User();
  constructor(private service:AngularServiceService) { 
  }
    
  ngOnInit(): void {
    this.user.userId=1105;
   // alert(JSON.stringify(this.user))
    this.service.list(this.user).subscribe(data=>{
      this.list1=data;
      console.log(this.list1);
    })
  }



}
