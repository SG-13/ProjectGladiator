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
public class VehicleInsurancePlan {

	@Id
	@SequenceGenerator(name = "seq_vecInPo", initialValue = 20201, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vecInPo")
	private int insurancePlanId;
	@Column
	private String insurancePlan;
	@Column
	private Double insuranceCoverageAmount;

	@OneToOne(mappedBy = "vehicleinsuranceplan", cascade = CascadeType.ALL)
	private VehicleInsuranceDetails vehicleinsurancedetails;

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

	public VehicleInsuranceDetails getVehicleinsurancedetails() {
		return vehicleinsurancedetails;
	}

	public void setVehicleinsurancedetails(VehicleInsuranceDetails vehicleinsurancedetails) {
		this.vehicleinsurancedetails = vehicleinsurancedetails;
	}

	@Override
	public String toString() {
		return "VehicleInsurancePlan [insurancePlanId=" + insurancePlanId + ", insurancePlan=" + insurancePlan
				+ ", insuranceCoverageAmount=" + insuranceCoverageAmount + "]";
	}

	
	
}
