package com.lti.dto;


import java.util.List;

import com.lti.model.TravelInsuranceDetails;
import com.lti.model.VehicleInsuranceDetails;

public class DashDto {
	private int userId;
    private int insurancePolicyId;
    private String insuranceStatus;
	private int insuranceDuration;
	private Double insurancePremium;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getInsurancePolicyId() {
		return insurancePolicyId;
	}
	public void setInsurancePolicyId(int insurancePolicyId) {
		this.insurancePolicyId = insurancePolicyId;
	}
	public String getInsuranceStatus() {
		return insuranceStatus;
	}
	public void setInsuranceStatus(String insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}
	public int getInsuranceDuration() {
		return insuranceDuration;
	}
	public void setInsuranceDuration(int insuranceDuration) {
		this.insuranceDuration = insuranceDuration;
	}
	public Double getInsurancePremium() {
		return insurancePremium;
	}
	public void setInsurancePremium(Double insurancePremium) {
		this.insurancePremium = insurancePremium;
	}
	
}

