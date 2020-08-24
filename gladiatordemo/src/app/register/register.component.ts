import { Component, OnInit } from '@angular/core';
import { UserDetails } from "../user-details";
import { Router } from "@angular/router";
import { DashboardComponent } from "../dashboard/dashboard.component";
import { AngularServiceService } from "../angular-service.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
   message:string;
   
   userPassword1:string
   userDetails=new UserDetails();
  constructor(private service : AngularServiceService, private router:Router) { }

  register()
  {
    
    if(this.userPassword1==this.userDetails.userPassword)
    {
     this.service.register(this.userDetails).subscribe( data=>{
        if(data.status=='SUCCESS')
        {
          //alert(JSON.stringify(data))
          this.router.navigate(['loginLink']);
        }
        else(data.status=='FAILURE')
        { 
         alert(JSON.stringify(data))      
        }  
     })

    }
    else
    {
      alert("User Password didn't match!")
    }

  }
    ngOnInit(): void {
  }

}
