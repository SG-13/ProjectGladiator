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

  message:string;
  amount:number;
  showRegisterForm:boolean=true;
  showPlanForm:boolean=false;
  showPremium:boolean=false;
  vehicle : VehicleDetails = new VehicleDetails();
  policy = Array<VehicleInsurancePlan>();
  buyinsurance : BuyVehicleInsurance = new BuyVehicleInsurance();

  constructor(private service : AngularServiceService,private router: Router) {}


  showform2(){
    console.log(this.vehicle);
    this.showRegisterForm=false;
    this.showPlanForm=true;
    
    this.service.saveVehicleDetails(this.vehicle).subscribe( data => {
      //alert(data.vehicleId);
      this.buyinsurance.registrationNumber=data.vehicleId;
    })
    
    this.service.fetchVehicleInsurancePlans().subscribe( data => {
      this.policy=data;
    })
    
  }

  calcPremium(){
    this.showPremium=true;
    this.policy.forEach(p => {
     
      if(p.insurancePlanId == this.buyinsurance.insurancePlanId)
      { 
        this.buyinsurance.insurancePremium=Math.round(((p.insuranceCoverageAmount*this.vehicle.vehicleCost)/8900)-(this.buyinsurance.duration*1234));
      }
    });
    
  }

  buyInsurance(){
    this.buyinsurance.userId=1105;
    this.service.saveVehicleInsuranceDetails(this.buyinsurance).subscribe( data =>{
      //alert(JSON.stringify(data));
    })

    this.router.navigate(['dashLink']);
  }

  //policy : Array<VehicleInsurancePlan> = [new VehicleInsurancePlan(123,"policy1",400),new VehicleInsurancePlan(456,"policy2",800),new VehicleInsurancePlan(789,"policy3",1200)];

}
