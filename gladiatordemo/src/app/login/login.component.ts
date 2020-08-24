import { Component, OnInit } from '@angular/core';
import { Login } from "../login-details";
import { Router } from "@angular/router";
import { AngularServiceService } from "../angular-service.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  onClick

  message:string;
    userId:string;
        userName:string;
  loginDetails=new Login();
  constructor(private service : AngularServiceService, private router:Router) { }

  login()
  {
  this.service.login(this.loginDetails).subscribe(
     data=>{
       if(data.status=='SUCCESS')
       {
         //alert(JSON.stringify(data))
      this.userId=data.userId;
        this.userName=data.name;

      sessionStorage.setItem("userId",this.userId);
      sessionStorage.setItem("userName",this.userName);
      this.router.navigate(['dashLink']);
   }
       else
       {
         this.message=data.message;       
         alert("wrong password")
        }
     })

    }
    ngOnInit(): void {
  }
}
