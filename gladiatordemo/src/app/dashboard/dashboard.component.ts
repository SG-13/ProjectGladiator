import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { User } from "../user";
import { AngularServiceService } from "../angular-service.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
show=false;
showb=false;
vehicleList:any=null;
travelList:any=null;
user:User=new User();
userName:string;

  constructor(private router: Router,private service:AngularServiceService) { }

  ngOnInit(): void {
    if(sessionStorage.getItem("userId")==null)
    {   this.router.navigate(['loginLink']);
    }
    if(sessionStorage.getItem("userId")=="10670596")
    {   this.router.navigate(['adminDashLink']);
    }
    
    this.user.userId=parseInt (sessionStorage.getItem("userId"));
    this.userName=sessionStorage.getItem("userName");
  //alert(JSON.stringify(this.user));
  
  this.service.fetchVehicledeatilsforUserDash(this.user).subscribe(data => {
    this.vehicleList=data;
   })

  this.service.fetchTraveldeatilsforUserDash(this.user).subscribe(data => {
  this.travelList=data;
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
