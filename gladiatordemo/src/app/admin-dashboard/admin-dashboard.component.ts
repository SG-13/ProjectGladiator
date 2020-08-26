import { Component, OnInit } from '@angular/core';
import { AngularServiceService } from "../angular-service.service";
import { UpdatedClaimStatus } from "../claim-status-update";
import { InsuranceStatus } from "../insurance-status-update";
import { Router } from "@angular/router";

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  constructor(private service:AngularServiceService,private router:Router) { }

  searchText:any;
  vehicleList:any;
  travelList:any;
  //claimdetails:AllClaimDetails=new AllClaimDetails();
  claimdetails:any;
  newClaimStatus:UpdatedClaimStatus = new UpdatedClaimStatus();
  newInsuranceStatus:InsuranceStatus = new InsuranceStatus();

  ngOnInit(): void {

    if(sessionStorage.getItem("userId")!="10670596")
    {   this.router.navigate(['loginLink']);
    }

    this.service.fetchAllVehicleInsurance().subscribe( data => {
      this.vehicleList=data;
    })

    this.service.fetchAllTravelInsurance().subscribe( data => {
      this.travelList=data;
    })  
    
    this.service.fetchAllClaimInsuranceDetails().subscribe( data => {
      this.claimdetails=data;
    })  
  }
  
  show1=true;
  show2=false;
  show3=false;
  
  showdiv1(){
    this.show1=true;
    this.show2=false;
    this.show3=false;
  }
  showdiv2(){
    this.show1=false;
    this.show2=true;
    this.show3=false;
  }

  showdiv3(){
    this.show1=false;
    this.show2=false;
    this.show3=true;
  }

  setClaimStatus(id,status,amount){
    this.newClaimStatus.claimId=id;
    this.newClaimStatus.status=status;
    this.newClaimStatus.claimAmount=amount;

    //alert(JSON.stringify(this.newClaimStatus));
    this.service.sendUpdatedClaimStatus(this.newClaimStatus).subscribe( data  =>{
      window.location.reload();
    })

  }

  setTravelInsuranceStatus(id,status){
    this.newInsuranceStatus.insurancePolicyId=id;
    this.newInsuranceStatus.status=status;
    //alert(JSON.stringify(this.newInsuranceStatus));
    this.service.sendUpdatedTravelInsuranceStatus(this.newInsuranceStatus).subscribe( data  =>{
      window.location.reload();
    })
  }

  
  setVehicleInsuranceStatus(id,status){
    this.newInsuranceStatus.insurancePolicyId=id;
    this.newInsuranceStatus.status=status;
    //alert(JSON.stringify(this.newInsuranceStatus));
    this.service.sendUpdatedVehicleInsuranceStatus(this.newInsuranceStatus).subscribe( data  =>{
      window.location.reload();
    })
  }

 
}
