package com.lti.dto;

import com.lti.model.VehicleInsuranceDetails;

public class BuyVehicleInsuranceDto {

	private int userId;
	private String registrationNumber;
	private int insurancePlanId;
	private int duration;
	private double insurancePremium;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public int getInsurancePlanId() {
		return insurancePlanId;
	}
	public void setInsurancePlanId(int insurancePlanId) {
		this.insurancePlanId = insurancePlanId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getInsurancePremium() {
		return insurancePremium;
	}
	public void setInsurancePremium(double insurancePremium) {
		this.insurancePremium = insurancePremium;
	}
	
}
