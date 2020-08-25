import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { PolicyId } from "./policy";
@Injectable({
  providedIn: 'root'
})
export class RenewService {
  constructor(private http: HttpClient) {  }
    
    getUserNameAndPolicyType(insurancePolicyId: PolicyId):Observable<any>
    {
        return this.http.post("http://localhost:8080/findUserByPolicyId",insurancePolicyId);
    }  

    checkPolicyId(insurancePolicyId: PolicyId): Observable<any>
    {
      return this.http.post("http://localhost:8080/checkVehiclePolicyId",insurancePolicyId);
    }

    addDuration(insuranceDuration: PolicyId): Observable<any>
    {
      return this.http.post("http://localhost:8080/renewInsurance",insuranceDuration);
    }

  }