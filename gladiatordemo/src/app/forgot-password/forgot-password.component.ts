import { Component, OnInit } from '@angular/core';
import { AngularServiceService } from "../angular-service.service";
import { ForgotPassword } from "../forgotPassword";
import { Router } from "@angular/router";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
constructor(private forgotPasswordService : AngularServiceService, private router:Router) { }

  show:boolean=true
  show1:boolean=false
  show2:boolean=false
  show3:boolean=false
  userOtp:any;
  userEmail:any;
  forgotPasswordDetails:ForgotPassword=new ForgotPassword()
  message:string
  message1:string
  message2:string
  message3:string
    
  

  getOTP()
  {
    this.userEmail=this.forgotPasswordDetails.userEmail
    //console.log(this.forgotPasswordDetails.userEmail)
    this.forgotPasswordService.verifyEmail(this.forgotPasswordDetails).subscribe(
     data=>{
       //alert(JSON.stringify(data))
       if(data.status=='SUCCESS')
       {
         this.message=""
         this.userOtp=data.userOTP
        //alert(JSON.stringify(data))
         this.show=false;
         this.show1=true;
         this.show2=false;
         this.show3=false;
         this.message=""
         this.message1=""
         this.message2=""
         this.message3=""
       }
       else
       {
         this.message=data.message
         this.message1=""
         this.message2=""
         this.message3=""  
        
       }
     })
  }

verifyOTP()
{
  if(this.userOtp==this.forgotPasswordDetails.userOTP) {

         this.show=false;
         this.show1=false;
         this.show2=true;
         this.show3=false;
         this.message=""
         this.message1=""
         this.message2=""
         this.message3=""
  }
  else
  {
    this.message1="OTP verification failed!"
    this.message=""
    this.message2=""
    this.message3=""
    
  }
}

resetPassword()
{
  if (this.forgotPasswordDetails.newUserPassword===this.forgotPasswordDetails.newUserPasswordConfirm) 
  {
    this.message2=""
    this.forgotPasswordService.forgotPassword(this.forgotPasswordDetails).subscribe(
      data=>{
      if(data.status=='SUCCESS')
       {
        
         this.show2=false;
         this.show3=true;
         setTimeout(() => {this.router.navigate(['loginLink'])}, 2000);
       }
       else
       {
         this.message2=data.message
         
          
       }
      }
    )  
  }
  else
  {
           this.message2="Password didn't match!"
           this.message1=""
           this.message=""
           this.message3=""
  }

}
  

  ngOnInit(): void {
  }
  
}
