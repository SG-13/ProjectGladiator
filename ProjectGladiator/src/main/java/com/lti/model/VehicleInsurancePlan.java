package com.lti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VehicleInsurancePlan {

	@Id
	@SequenceGenerator(name = "seq_vecInPo", initialValue = 20201, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vecInPo")
	private int insurancePlanId;
	@Column
	private String insurancePlan;
	@Column
	private double insuranceCoverageAmount;
	@JsonIgnore
	@OneToMany(mappedBy = "vehicleinsuranceplan", cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	private List<VehicleInsuranceDetails> vehicleinsurancedetails;

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

	public List<VehicleInsuranceDetails> getVehicleinsurancedetails() {
		return vehicleinsurancedetails;
	}

	public void setVehicleinsurancedetails(List<VehicleInsuranceDetails> vehicleinsurancedetails) {
		this.vehicleinsurancedetails = vehicleinsurancedetails;
	}

}
