import { Component, OnInit } from '@angular/core';
import { Claim } from "../claim";
import { AngularServiceService } from "../angular-service.service";
import { Router } from "@angular/router";
@Component({
  selector: 'app-claim-request',
  templateUrl: './claim-request.component.html',
  styleUrls: ['./claim-request.component.css']
})

export class ClaimRequestComponent implements OnInit {
claim:Claim=new Claim();
message:string;
date: Date = new Date();
FromDate = this.date.getFullYear() + '-' + ('0' + (this.date.getMonth() + 1)).slice(-2) + '-' + ('0' + this.date.getDate()).slice(-2);
visible=false;
visible1=false;
sucsMsg=false;
polDiv=true;
showerr=false;
id:number;
errMsg:string="Invalid PolicyId";
  
constructor(private service:AngularServiceService,private router: Router) {}

  ngOnInit(): void {
    if(sessionStorage.getItem("userId")==null)
    {   this.router.navigate(['loginLink']);
    }
  }
  check(){
    //alert(JSON.stringify(this.claim))
    this.service.exist(this.claim).subscribe(data=>{
     // alert(JSON.stringify(data));
      if(data.status=="Success"){
       // this.message=data.message;
        this.visible=true;
        this.showerr=false;
      }
      else{
        this.showerr=true;
      }
    })

  }
  register(){
    //alert(JSON.stringify(this.claim));
    this.service.add(this.claim).subscribe(data=>{
     // alert(JSON.stringify(data));
      if(data.status="Success"){
        this.message="Claim Registered"
       // login.reset();
        this.visible=false;
        this.sucsMsg=true;
        this.id=data.claimId;
        this.polDiv=false;
      }
    })

}

}

