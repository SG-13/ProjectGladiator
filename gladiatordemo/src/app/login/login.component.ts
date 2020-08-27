import { Component, OnInit } from '@angular/core';
import { Login } from "../login-details";
import { Router } from "@angular/router";
import { AngularServiceService } from "../angular-service.service";
import { BuyVehicleInsurance } from "../buy-vehicle-insurance";
import { BuyTravelInsurance } from '../buy-travel-insurance';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  message:string;
  userId:string;
  userName:string;
  loginDetails=new Login();
  constructor(private service : AngularServiceService, private router:Router) { }

  login()
  {
    if(this.loginDetails.userId==10670596 && this.loginDetails.userPassword=="Admin@123"){
      sessionStorage.setItem("userId","10670596");
      sessionStorage.setItem("userName","Saransh");
      this.router.navigate(['adminDashLink']);
      location.reload();
    }
  this.service.login(this.loginDetails).subscribe(
     data=>{
       
       if(data.status=='SUCCESS')
       { 
         //alert(JSON.stringify(data))
        this.userId=data.userId;
        this.userName=data.userName;

      sessionStorage.setItem("userId",this.userId);
      sessionStorage.setItem("userName",this.userName);
      window.location.reload();
   
   }
       else
       {
         this.message=data.message;       
         //alert("wrong password")
        }
     })

    }
    ngOnInit(): void {
      if(sessionStorage.getItem("userId")!=null)
    {   
    if(localStorage.getItem("buyinsurance") !== null){
      var buyinsurance : BuyVehicleInsurance = JSON.parse(localStorage.getItem("buyinsurance"));
      buyinsurance.userId=parseInt(sessionStorage.getItem("userId"));
      localStorage.clear();
      localStorage.setItem("buyinsurance", JSON.stringify(buyinsurance));
      this.router.navigate(['paymentLink']);
     }

     else if(localStorage.getItem("buyinsurance1") !== null){
      var buyinsurance1 : BuyTravelInsurance = JSON.parse(localStorage.getItem("buyinsurance1"));
      buyinsurance1.userId=parseInt(sessionStorage.getItem("userId"));
      localStorage.clear();
      localStorage.setItem("buyinsurance1", JSON.stringify(buyinsurance1));
      this.router.navigate(['paymentLink']);
     }

     else{
      this.router.navigate(['dashLink']);

     }
    }

  }
}
