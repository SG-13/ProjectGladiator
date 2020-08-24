import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent  {
  show2:boolean=true
  show1:boolean
  show:boolean;
  click()
  {
    this.show=true
    this.show2=false
  }
  click1()
  {
    this.show=false;
    this.show1=true;

  }
  forgotPassword()
  {
    
  }
}
