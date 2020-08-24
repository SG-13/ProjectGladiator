import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { VehicleDetails } from "./vehicle-details";
import { BuyVehicleInsurance } from "./buy-vehicle-insurance";
import { TravelDetails } from "./travel-details";
import { BuyTravelInsurance } from "./buy-travel-insurance";
import { Login } from "./login-details";
import { UserDetails } from "./user-details";

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

}
