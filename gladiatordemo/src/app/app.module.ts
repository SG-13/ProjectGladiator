import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClaimComponent } from './claim/claim.component';
import { ClaimRequestComponent } from './claim-request/claim-request.component';
import { ClaimStatusComponent } from './claim-status/claim-status.component';
import { HomeComponent } from './home/home.component';
import { ChooseInsuranceComponentComponent } from './choose-insurance-component/choose-insurance-component.component';
import { TravelComponentComponent } from './travel-component/travel-component.component';
import { VehicleComponentComponent } from './vehicle-component/vehicle-component.component';
import { RenewComponent } from './renew/renew.component';
import { LoginComponent } from './login/login.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    ClaimComponent,
    ClaimRequestComponent,
    ClaimStatusComponent,
    HomeComponent,
    ChooseInsuranceComponentComponent,
    TravelComponentComponent,
    VehicleComponentComponent,
    RenewComponent,
    LoginComponent,
    ForgotPasswordComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }