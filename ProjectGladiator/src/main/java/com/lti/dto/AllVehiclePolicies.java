package com.lti.dto;

import java.time.LocalDate;
import java.util.List;


public class AllVehiclePolicies {
	
	private int insurancePolicyId;
	private LocalDate insuranceDuration;
	private String insuranceStatus;
	private double insurancePremium;
	private String vehicleRegNo;
	private int insurancePlanId;
	private int userId;
	
	
	public int getInsurancePolicyId() {
		return insurancePolicyId;
	}
	public void setInsurancePolicyId(int insurancePolicyId) {
		this.insurancePolicyId = insurancePolicyId;
	}
	public LocalDate getInsuranceDuration() {
		return insuranceDuration;
	}
	public void setInsuranceDuration(LocalDate insuranceDuration) {
		this.insuranceDuration = insuranceDuration;
	}
	public String getInsuranceStatus() {
		return insuranceStatus;
	}
	public void setInsuranceStatus(String insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}
	public double getInsurancePremium() {
		return insurancePremium;
	}
	public void setInsurancePremium(double insurancePremium) {
		this.insurancePremium = insurancePremium;
	}
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}
	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}
	public int getInsurancePlanId() {
		return insurancePlanId;
	}
	public void setInsurancePlanId(int insurancePlanId) {
		this.insurancePlanId = insurancePlanId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	

}
