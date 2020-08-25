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
  
  logout(){
    sessionStorage.clear();
    this.router.navigate(['']);
  }

  constructor(private router: Router) {
    if(sessionStorage.getItem("userId")==null)
    {   this.showlogout=false;
    }
    else{
      this.showlogout=true;
    }
   }



}
