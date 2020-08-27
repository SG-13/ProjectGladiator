import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChooseInsuranceComponentComponent } from "./choose-insurance-component/choose-insurance-component.component";
import { ClaimRequestComponent } from "./claim-request/claim-request.component";
import { ClaimStatusComponent } from "./claim-status/claim-status.component";
import { VehicleComponentComponent } from "./vehicle-component/vehicle-component.component";
import { TravelComponentComponent } from "./travel-component/travel-component.component";
import { HomeComponent } from "./home/home.component";
import { RenewComponent } from "./renew/renew.component";
import { LoginComponent } from "./login/login.component";
import { ForgotPasswordComponent } from "./forgot-password/forgot-password.component";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { AboutUsComponent } from "./about-us/about-us.component";
import { ContactUsComponent } from "./contact-us/contact-us.component";
import { RegisterComponent } from './register/register.component';
import { LogoutComponent } from './logout/logout.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { PaymentComponent } from './payment/payment.component';
import { FaqPageComponent } from './faq-page/faq-page.component';
const routes: Routes = [
  {
    path:'buyLink', component:ChooseInsuranceComponentComponent
  },
  {
    path:'request',component:ClaimRequestComponent
  },
  {
    path:'status',component:ClaimStatusComponent
  },
  {
    path:'vehicleLink',component:VehicleComponentComponent
  },
  {
    path:'travelLink',component:TravelComponentComponent
  },
  {
    path:'',component:HomeComponent
  },
  {
  path:'renewLink',component:RenewComponent
  },
  {
    path:'loginLink',component:LoginComponent
  },
  {
    path:'forgotLink',component:ForgotPasswordComponent
  },
  {
    path:'dashLink',component:DashboardComponent
  },
  {
    path:'aboutLink',component:AboutUsComponent
  },
  {
  path:'contactLink',component:ContactUsComponent
  },
  {
    path:'registerLink',component:RegisterComponent
  },
  {
    path:'adminDashLink',component:AdminDashboardComponent
  },
  {
    path:'paymentLink',component:PaymentComponent
  },
  {
    path:'faqLink',component:FaqPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
