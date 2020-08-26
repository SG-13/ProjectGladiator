import { Component, OnInit } from '@angular/core';
import { UserDetails } from "../user-details";
import { Router } from "@angular/router";
import { AngularServiceService } from "../angular-service.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
   message:string;
   showerr:boolean=false; 
   passerr:boolean=false;
   showForm:boolean=true; 
   showsucc:boolean=false;   
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
          
          this.showerr=false;
          this.passerr=false;
          this.showForm=false;
          this.showsucc=true;
          setTimeout(() => {this.router.navigate(['loginLink'])}, 4000);

        }
        else(data.status=='FAILURE')
        { this.passerr=false;
          this.showerr=true;
          this.message=data.message;
         //alert(JSON.stringify(data))      
        }  
     })

    }
    else
    { this.passerr=true;
      this.showerr=false;
      //alert("User Password didn't match!")
    }

  }
    ngOnInit(): void {
  }

}
