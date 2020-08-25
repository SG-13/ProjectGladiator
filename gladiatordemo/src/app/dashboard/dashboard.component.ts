import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { User } from "../user";
import { Dash } from "../dash";
import { AngularServiceService } from "../angular-service.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
show=false;
showb=false;
dash1:Dash[];
user:User=new User();
  constructor(private router: Router,private service:AngularServiceService) { }

  ngOnInit(): void {
    if(sessionStorage.getItem("userId")==null)
    {   this.router.navigate(['loginLink']);
    }
    this.user.userId=parseInt (sessionStorage.getItem("userId"));
  //alert(JSON.stringify(this.user));
    this.service.dashs(this.user).subscribe((dash1:Dash[])=>{
      this.dash1=dash1;
     
  })
    
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
