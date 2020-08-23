package com.lti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TravelInsurancePlan {

	@Id
	@SequenceGenerator(name = "seq_traInPo", initialValue = 21201, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_traInPo")
	private int insurancePlanId;
	@Column
	private String insurancePlan;
	@Column
	private double insuranceCoverageAmount;
	@JsonIgnore
	@OneToMany(mappedBy = "travelinsuranceplan", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<TravelInsuranceDetails> travelinsurancedetails;

	
	
	public int getInsurancePlanId() {
		return insurancePlanId;
	}

	public void setInsurancePlanId(int insurancePlanId) {
		this.insurancePlanId = insurancePlanId;
	}

	public String getInsurancePlan() {
		return insurancePlan;
	}

	public void setInsurancePlan(String insurancePlan) {
		this.insurancePlan = insurancePlan;
	}

	public double getInsuranceCoverageAmount() {
		return insuranceCoverageAmount;
	}

	public void setInsuranceCoverageAmount(double insuranceCoverageAmount) {
		this.insuranceCoverageAmount = insuranceCoverageAmount;
	}

	public List<TravelInsuranceDetails> getTravelinsurancedetails() {
		return travelinsurancedetails;
	}

	public void setTravelinsurancedetails(List<TravelInsuranceDetails> travelinsurancedetails) {
		this.travelinsurancedetails = travelinsurancedetails;
	}

	
}
