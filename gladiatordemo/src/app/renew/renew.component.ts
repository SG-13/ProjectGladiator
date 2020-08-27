import { Component, OnInit, Injectable } from '@angular/core';
import { AngularServiceService } from "../angular-service.service";
import { RenewDetails } from "../renew";
import { PolicyId } from "../policy";
import { Router } from '@angular/router';

@Component({
  selector: 'app-renew',
  templateUrl: './renew.component.html',
  styleUrls: ['./renew.component.css']
})


export class RenewComponent implements OnInit {


  ngOnInit(): void {
    if (sessionStorage.getItem("userId") == null) {
      this.router.navigate(['loginLink']);
    }
  }

  constructor(private service: AngularServiceService, private router: Router) { }

  public show: boolean = false;
  public first: boolean = true;
  public showm: boolean = false;
  showerr1: boolean = false;
  showerr2: boolean = false;
  showerr3: boolean = false;


  policyId = new PolicyId();

 // userId: number = parseInt(sessionStorage.getItem("userId"));
  userName: string;
  policyType: string;
  duration: string;
  premium:number;

  renew(renewForm) {
  }

  policyName: string = "Vehicle Insurance";
  renewDetails = new RenewDetails();

  myFunc() {
    this.service.checkPolicyId(this.policyId).subscribe(
      data => {
        if (data == true) {
          this.checkUser();
        }
        else {
          this.first = true;
          this.show = false;
          this.showerr1 = true;
          this.showerr2 = false;
          this.showerr3 = false;
        }
      }
    )
    this.service.getUserNameAndPolicyType(this.policyId).subscribe(
      data => {
        this.userName = data.userName;
        this.policyType = data.policyType;
        this.premium = data.insurancePremium;
      })
  }

  checkUser() {
    this.policyId.userId = parseInt(sessionStorage.getItem("userId"));

    this.service.checkUser(this.policyId).subscribe(
      data => {
        if (data.status == "SUCCESS") {
          //alert(data.status);
          this.checkDuration();
        }
        else {
          this.showerr1 = false;
          this.showerr2 = true;
          this.showerr3 = false;
        }
      }
    )
  }


checkDuration(){

  this.service.checkIfRenewable(this.policyId).subscribe( data => {
    if(data==true){
      this.first = false;
      this.showerr1 = false;
      this.showerr2 = false;
      this.showerr3 = false;
      this.show = true;
    }
    else{
      this.showerr1 = false;
      this.showerr2 = false;
      this.showerr3 = true;
    }
  })

}

  myFunc2() {
    this.showm = true;
    this.show = false;
    localStorage.setItem("policyId",JSON.stringify(this.policyId));
    localStorage.setItem("premium",JSON.stringify(this.premium));
    this.router.navigate(['paymentLink']);
  }

}