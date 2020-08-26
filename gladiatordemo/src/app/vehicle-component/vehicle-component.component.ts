import { Component, OnInit } from '@angular/core';
import { VehicleInsurancePlan } from "../vehicle-insurance-plan";
import { AngularServiceService } from "../angular-service.service";
import { VehicleDetails } from "../vehicle-details";
import { BuyVehicleInsurance } from "../buy-vehicle-insurance";
import { Router } from '@angular/router';

@Component({
  selector: 'app-vehicle-component',
  templateUrl: './vehicle-component.component.html',
  styleUrls: ['./vehicle-component.component.css']
})
export class VehicleComponentComponent {
  date : Date = new Date();
  FromDate = this.date.getFullYear() + '-' + ('0' + (this.date.getMonth() + 1)).slice(-2) + '-' + ('0' + this.date.getDate()).slice(-2);
  minCost:number=30000;
  showRegisterForm:boolean=true;
  showPlanForm:boolean=false;
  showPremium:boolean=false;
  showMessage:boolean=false;
  vehicle : VehicleDetails = new VehicleDetails();
  policy = Array<VehicleInsurancePlan>();
  buyinsurance : BuyVehicleInsurance = new BuyVehicleInsurance();
  message:string;
  maxCoverage:number;

  constructor(private service : AngularServiceService,private router: Router) {}


  showform2(){
    console.log(this.vehicle);
    
    this.service.saveVehicleDetails(this.vehicle).subscribe( data => {
      //alert(data.status);
      
      if(data.status=="SUCCESS"){
        this.buyinsurance.registrationNumber=this.vehicle.registrationNumber;
        this.showRegisterForm=false;
        this.showPlanForm=true;
        this.showPolicies();
        this.showMessage=false;
      }
      else{
        this.showMessage=true;
        this.message=data.message;
      }
      
    })
    
  }

  showPolicies(){
    this.service.fetchVehicleInsurancePlans().subscribe( data => {
      this.policy=data;
    })
  }
  
    

  calcPremium(){
    this.showPremium=true;
    this.policy.forEach(p => {
      if(p.insurancePlanId == this.buyinsurance.insurancePlanId)
      { 
        this.buyinsurance.insurancePremium=Math.round((((p.insuranceCoverageAmount/10000) *this.vehicle.vehicleCost)*0.4*18)-(this.buyinsurance.duration*37));
      }
    });
    
  }

  buyInsurance(){
    
    if(sessionStorage.getItem("userId")==null){
      localStorage.setItem("buyinsurance", JSON.stringify(this.buyinsurance));
      this.router.navigate(['loginLink']);
    }
    else{
   // this.buyinsurance.userId=1105;
    this.buyinsurance.userId=parseInt(sessionStorage.getItem("userId"));
    this.service.saveVehicleInsuranceDetails(this.buyinsurance).subscribe( data =>{
      //alert(JSON.stringify(data));
    })
    this.router.navigate(['dashLink']);
  }
  }
  //policy : Array<VehicleInsurancePlan> = [new VehicleInsurancePlan(123,"policy1",400),new VehicleInsurancePlan(456,"policy2",800),new VehicleInsurancePlan(789,"policy3",1200)];

}
