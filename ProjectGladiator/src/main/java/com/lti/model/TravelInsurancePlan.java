package com.lti.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class TravelInsurancePlan {

	@Id
	@SequenceGenerator(name = "seq_traInPo", initialValue = 21201, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_traInPo")
	private int insurancePlanId;
	@Column
	private String insurancePlan;
	@Column
	private Double insuranceCoverageAmount;
	
	@OneToOne(mappedBy = "travelinsuranceplan", cascade=CascadeType.ALL)
	private TravelInsuranceDetails travelinsurancedetails;

	
	
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

	public Double getInsuranceCoverageAmount() {
		return insuranceCoverageAmount;
	}

	public void setInsuranceCoverageAmount(Double insuranceCoverageAmount) {
		this.insuranceCoverageAmount = insuranceCoverageAmount;
	}

	public TravelInsuranceDetails getTravelinsurancedetails() {
		return travelinsurancedetails;
	}

	public void setTravelinsurancedetails(TravelInsuranceDetails travelinsurancedetails) {
		this.travelinsurancedetails = travelinsurancedetails;
	}

	@Override
	public String toString() {
		return "TravelInsurancePlan [insurancePlanId=" + insurancePlanId + ", insurancePlan=" + insurancePlan
				+ ", insuranceCoverageAmount=" + insuranceCoverageAmount + "]";
	}
	
	
	
}
