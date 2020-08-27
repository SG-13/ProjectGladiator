import { Component, OnInit } from '@angular/core';
import { AngularServiceService } from "../angular-service.service";
import { BuyVehicleInsurance } from "../buy-vehicle-insurance";
import { Router } from "@angular/router";
import { BuyTravelInsurance } from "../buy-travel-insurance";
import { PolicyId } from '../policy';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  constructor(private service:AngularServiceService,private router:Router) { }

  premium:number=0;
  showpaydetails:boolean=true;
  showconfirm:boolean=false;
  ngOnInit(): void {
    
    if(localStorage.getItem("buyinsurance") !== null){
      var buyinsurance : BuyVehicleInsurance = JSON.parse(localStorage.getItem("buyinsurance"));
      this.premium=buyinsurance.insurancePremium;
    }

    else if(localStorage.getItem("buyinsurance1") !== null){
      var buyinsurance1 : BuyTravelInsurance = JSON.parse(localStorage.getItem("buyinsurance1"));
      this.premium=buyinsurance1.insurancePremium;
    }

    else if(localStorage.getItem("premium")!==null){
      this.premium = JSON.parse(localStorage.getItem("premium"));
    }

    else{
      this.router.navigate(['']);
    }
    
  }

  showconfirmPayment(){
    this.showpaydetails=false;
    this.showconfirm=true;
  }

  completePayment(){
    
    if(localStorage.getItem("buyinsurance") !== null){
      var buyinsurance : BuyVehicleInsurance = JSON.parse(localStorage.getItem("buyinsurance"));
      this.service.saveVehicleInsuranceDetails(buyinsurance).subscribe( data =>{
        localStorage.clear();
        this.router.navigate(['dashLink']);
      })
     }

     else if(localStorage.getItem("buyinsurance1") !== null){
      var buyinsurance1 : BuyTravelInsurance = JSON.parse(localStorage.getItem("buyinsurance1"));
      this.service.saveTravelInsuranceDetails(buyinsurance1).subscribe( data =>{
        localStorage.clear();
        this.router.navigate(['dashLink']);
      })
    }

    else if(localStorage.getItem("premium") !== null){
      var buyinsurance1 : BuyTravelInsurance = JSON.parse(localStorage.getItem("buyinsurance1"));
      var policyId : PolicyId = JSON.parse(localStorage.getItem("policyId"));
      this.service.addDuration(policyId).subscribe( data => {
        localStorage.clear();
        this.router.navigate(['dashLink']);
        })
    }

}

}
