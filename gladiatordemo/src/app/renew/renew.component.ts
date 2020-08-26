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


  policyId = new PolicyId();

 // userId: number = parseInt(sessionStorage.getItem("userId"));
  userName: String;
  policyType: String;
  duration: any;

  renew(renewForm) {
  }

  policyName: any = "Motor Insurance";
  renewDetails = new RenewDetails();
  checkUser() {
    this.policyId.userId = parseInt(sessionStorage.getItem("userId"));

    this.service.checkUser(this.policyId).subscribe(
      data => {
        if (data.status == "SUCCESS") {
          //alert(data.status);
          this.first = false;
          this.show = true;
          this.showerr1 = false;
          this.showerr2 = false;
        }
        else {
          this.showerr1 = false;
          this.showerr2 = true;
        }
      }

    )

  }

  myFunc() {
    this.service.checkPolicyId(this.policyId).subscribe(
      data => {
        //alert(JSON.stringify(data));
        if (data == true) {
          this.checkUser();
          //alert(" You entered wrong Policy Id");
        }
        else {
          this.first = true;
          this.show = false;
          this.showerr1 = true;
          this.showerr2 = false;
        }
      }
    )
    this.service.getUserNameAndPolicyType(this.policyId).subscribe(
      data => {
        this.userName = data.userName;
        this.policyType = data.policyType;
      })
  }


  myFunc2() {
    this.showm = true;
    this.show = false;
    this.service.addDuration(this.policyId).subscribe(
      data => {
        this.duration = data.insuranceDuration;
        //alert("Your new insurance duration is extended by "+ this.duration+" years.")
      })
  }

}