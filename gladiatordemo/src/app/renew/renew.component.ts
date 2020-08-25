import { Component, OnInit, Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { RenewService } from "../service";
import { RenewDetails  } from "../renew";
import { PolicyId  } from "../policy";
import { Router } from '@angular/router';

@Component({
  selector: 'app-renew',
  templateUrl: './renew.component.html',
  styleUrls: ['./renew.component.css']
})


export class RenewComponent implements OnInit {


  ngOnInit(): void {
    if(sessionStorage.getItem("userId")==null)
    {   this.router.navigate(['loginLink']);
    }
  }

  constructor(private service:RenewService, private router: Router){}

  public show:boolean = false;
  public first:boolean = true;
  public showm:boolean = false;
  showerr:boolean=false;
  errMsg:string="Invalid PolicyId";

  policyId = new PolicyId();

  userName: String;
  policyType:String;
duration:any;
  renew(renewForm){

  }

policyName:any = "Motor Insurance";
renewDetails=new RenewDetails();
  myFunc()
  {
  this.service.checkPolicyId(this.policyId).subscribe(
  data =>{
  if (data===false)
  {
    this.first=true;
    this.show= false;
    this.showerr=true;   
//alert(" You entered wrong Policy Id");
  }
  else{
    this.first=false;
    this.show= true;
    this.showerr=false;
  }
  
}
  
)
this.service.getUserNameAndPolicyType(this.policyId).subscribe(
  data => { 
   this.userName= data.userName;
   this.policyType= data.policyType;
  })
  }
  
  
  myFunc2()
  {
    this.showm=true;
this.show= false;
this.service.addDuration(this.policyId).subscribe(
  data => { 
   this.duration= data.insuranceDuration;
  //alert("Your new insurance duration is extended by "+ this.duration+" years.")
  })
  }
  
}