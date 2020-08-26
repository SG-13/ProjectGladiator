import { Component, OnInit } from '@angular/core';
import { List } from "../listl";
import { AngularServiceService } from "../angular-service.service";
import { User } from "../user";
import { Router } from "@angular/router";

@Component({
  selector: 'app-claim-status',
  templateUrl: './claim-status.component.html',
  styleUrls: ['./claim-status.component.css']
})
export class ClaimStatusComponent implements OnInit {

  list1=Array<List>();
  user:User=new User();
  constructor(private service:AngularServiceService,private router: Router) { 
  }
    
  ngOnInit(): void {
    if(sessionStorage.getItem("userId")==null)
    {   
      this.router.navigate(['loginLink']);
    }
    
    this.user.userId=parseInt(sessionStorage.getItem("userId"))
    // alert(JSON.stringify(this.user))
    this.service.fetchAllClaimDetailsForUser(this.user).subscribe(data=>{
      this.list1=data;
      console.log(this.list1);
    })
  }



}
