package com.lti.dto;

import java.time.LocalDate;

import com.lti.model.TravelInsuranceDetails;

public class BuyTravelInsuranceDto {
	
	private int userId;
	private int travelId;
	private int insurancePlanId;
	private LocalDate insuranceDuration;
    private double insurancePremium;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTravelId() {
		return travelId;
	}
	public void setTravelId(int travelId) {
		this.travelId = travelId;
	}
	public int getInsurancePlanId() {
		return insurancePlanId;
	}
	public void setInsurancePlanId(int insurancePlanId) {
		this.insurancePlanId = insurancePlanId;
	}
	
	public LocalDate getInsuranceDuration() {
		return insuranceDuration;
	}
	public void setInsuranceDuration(LocalDate insuranceDuration) {
		this.insuranceDuration = insuranceDuration;
	}
	public double getInsurancePremium() {
		return insurancePremium;
	}
	public void setInsurancePremium(double insurancePremium) {
		this.insurancePremium = insurancePremium;
	}
	
}
