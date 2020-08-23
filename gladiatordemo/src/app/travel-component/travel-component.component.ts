import { Component, OnInit } from '@angular/core';
import { TravelInsurancePlan } from "../travel-insurance-plan";
import { AngularServiceService } from "../angular-service.service";
import { TravelDetails } from "../travel-details";
import { BuyTravelInsurance } from "../buy-travel-insurance";
import { Router } from '@angular/router';


@Component({
  selector: 'app-travel-component',
  templateUrl: './travel-component.component.html',
  styleUrls: ['./travel-component.component.css']
})
export class TravelComponentComponent {

  message:string;
  amount:number;
  showRegisterForm:boolean=true;
  showPlanForm:boolean=false;
  showPremium:boolean=false;
  policy = Array<TravelInsurancePlan>();
  travel : TravelDetails = new TravelDetails();
  buyinsurance : BuyTravelInsurance = new BuyTravelInsurance();
  
  

  constructor(private service: AngularServiceService,private router: Router) { }


 showform2(){
   this.buyinsurance.insuranceDuration=this.travel.endDateOfJourney;
   console.log(this.travel)
    this.showRegisterForm=false;
    this.showPlanForm=true;

    this.service.saveTravelDetails(this.travel).subscribe( data => {
      //alert(data.id);
      this.buyinsurance.travelId=data.id;
    })

    this.service.fetchTravelInsurancePlans().subscribe( data => {
      //alert(JSON.stringify(data));
      this.policy=data;
    })
  }


  calcPremium(){
    this.showPremium=true;
    this.policy.forEach(p => {
     
      if(p.insurancePlanId == this.buyinsurance.insurancePlanId)
      { 
        this.buyinsurance.insurancePremium=Math.round((((p.insuranceCoverageAmount/100000)*this.travel.travelCost)/850));
      }
    });
    
  }
 
  buyInsurance(){
    this.buyinsurance.userId=1105;
    this.service.saveTravelInsuranceDetails(this.buyinsurance).subscribe( data =>{
      //alert(JSON.stringify(data));
    })

    this.router.navigate(['dashLink']);
  }

}
