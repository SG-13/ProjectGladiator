import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { Ng2SearchPipeModule } from 'ng2-search-filter';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
import { RegisterComponent } from './register/register.component';
import { LogoutComponent } from './logout/logout.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { PaymentComponent } from './payment/payment.component';
import { FaqPageComponent } from './faq-page/faq-page.component';

@NgModule({
  declarations: [
    AppComponent,
    ClaimRequestComponent,
    ClaimStatusComponent,
    HomeComponent,
    ChooseInsuranceComponentComponent,
    TravelComponentComponent,
    VehicleComponentComponent,
    RenewComponent,
    LoginComponent,
    ForgotPasswordComponent,
    DashboardComponent,
    RegisterComponent,
    LogoutComponent,
    AdminDashboardComponent,
    PaymentComponent,
    FaqPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    Ng2SearchPipeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
