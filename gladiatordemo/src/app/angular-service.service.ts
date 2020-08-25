import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { VehicleDetails } from "./vehicle-details";
import { BuyVehicleInsurance } from "./buy-vehicle-insurance";
import { TravelDetails } from "./travel-details";
import { BuyTravelInsurance } from "./buy-travel-insurance";
import { Login } from "./login-details";
import { UserDetails } from "./user-details";
import { IdStatus } from "./IdStatus";
import { Claim } from "./claim";
import { List } from "./listl";;
import { User } from "./user";
import { UpdatedClaimStatus } from "./claim-status-update";
import { InsuranceStatus } from "./insurance-status-update";
import { Dash } from "./dash";
import { ForgotPassword } from "./forgotPassword";

@Injectable({
  providedIn: 'root'
})
export class AngularServiceService {

  constructor(private http : HttpClient) {}

  
  fetchVehicleInsurancePlans():Observable<any>{
    let url='http://localhost:8080/showVehicleInsurancePlan';
    return this.http.get(url);
  }

  fetchTravelInsurancePlans():Observable<any>{
    let url='http://localhost:8080/showTravelInsurancePlan';
    return this.http.get(url);
  }

  saveVehicleDetails(vd:VehicleDetails):Observable<any>{
    let url='http://localhost:8080/addVehicleDetails';
    return this.http.post(url,vd);
  }

  saveVehicleInsuranceDetails(bvi:BuyVehicleInsurance):Observable<any>{
    let url='http://localhost:8080/buyVehicleInsurance';
    return this.http.post(url,bvi);
  }

  saveTravelDetails(td:TravelDetails):Observable<any>{
    let url='http://localhost:8080/addTravelDetails';
    return this.http.post(url,td);
  }

  saveTravelInsuranceDetails(bti:BuyTravelInsurance):Observable<any>{
    let url='http://localhost:8080/buyTravelInsurance';
    return this.http.post(url,bti);
  }

  login(login:Login):Observable<any>
  {
      return this.http.post("http://localhost:8080/login",login);
  }  

  register(userDetails:UserDetails):Observable<any>
  {
      return this.http.post("http://localhost:8080/register",userDetails);
  }  

  exist(claim:Claim):Observable<any>{
    let url="http://localhost:8080/check";
    return this.http.post<IdStatus>(url,claim);
  }
  
  add(claim:Claim):Observable<any>{
    let url="http://localhost:8080/add";
    return this.http.post<IdStatus>(url,claim);
  }
  
  list(user:User):Observable<any>{
    let url="http://localhost:8080/list"
    return this.http.post<List>(url,user);
  }

  fetchAllVehicleInsurance():Observable<any>{
    let url='http://localhost:8080/getAllVehiclePolicies';
    return this.http.get(url);
  }

  fetchAllTravelInsurance():Observable<any>{
    let url='http://localhost:8080/getAllTravelPolicies';
    return this.http.get(url);
  }

  fetchAllClaimInsuranceDetails():Observable<any>{
    let url='http://localhost:8080/showAllClaimDetails';
    return this.http.get(url);
  }

  sendUpdatedClaimStatus(newClaimStatus:UpdatedClaimStatus):Observable<any>{
    let url='http://localhost:8080/updateClaimStatus';
    return this.http.post(url,newClaimStatus);
  }

  sendUpdatedTravelInsuranceStatus(newInsuranceStatus:InsuranceStatus):Observable<any>{
    let url='http://localhost:8080/updateTravelInsuranceStatus';
    return this.http.post(url,newInsuranceStatus);
  }

  sendUpdatedVehicleInsuranceStatus(newInsuranceStatus:InsuranceStatus):Observable<any>{
    let url='http://localhost:8080/updateVehicleInsuranceStatus';
    return this.http.post(url,newInsuranceStatus);
  }

  dashs(user:User):Observable<any>{
    let url="http://localhost:8080/getAllInsurance"
    return this.http.post<Dash>(url,user);
  }

  verifyEmail(forgotPasswordDetails:ForgotPassword):Observable<any>
  {
      return this.http.post("http://localhost:8080/verifyEmail",forgotPasswordDetails);
  }
  forgotPassword(forgotPasswordDetails:ForgotPassword):Observable<any>
  {
      return this.http.post("http://localhost:8080/forgotPassword",forgotPasswordDetails);
  }  


}
