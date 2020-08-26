import { Component } from '@angular/core';
import { Router } from "@angular/router";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'LTI Insurance';
  user:string=sessionStorage.getItem("userName");
  showlogout:boolean=false;
  showAdminDash:boolean=false;
  
  logout(){
    sessionStorage.clear();
    this.router.navigate(['homeLink']);
    window.location.reload();
  }



  constructor(private router: Router) {
    if(sessionStorage.getItem("userId")==null)
    {   this.showlogout=false;
    }
    else{
      this.showlogout=true;
      if(sessionStorage.getItem("userId")=="10670596"){
        this.showAdminDash=true;
      }
      else{
        this.showAdminDash=true;
      }
    }
   }



}
