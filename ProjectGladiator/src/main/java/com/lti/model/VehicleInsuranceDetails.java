package com.lti.model;



import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class VehicleInsuranceDetails {

	@Id
	@SequenceGenerator(name = "seq_vecInDe", initialValue = 50201, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vecInDe")
	private int insurancePolicyId;
	@Column
	private LocalDate insuranceDuration;
	@Column
	private String insuranceStatus;
	@Column
	private Double insurancePremium;
	
	@OneToMany(mappedBy = "vehicleinsurancedetails", cascade=CascadeType.ALL)
	private List<ClaimDetails> claimdetails;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_regNo")
	private VehicleDetails vehicledetails;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insurance_planId")
	private VehicleInsurancePlan vehicleinsuranceplan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserDetails user;
	
	

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

	public Double getInsurancePremium() {
		return insurancePremium;
	}

	public void setInsurancePremium(Double insurancePremium) {
		this.insurancePremium = insurancePremium;
	}

	public List<ClaimDetails> getClaimdetails() {
		return claimdetails;
	}

	public void setClaimdetails(List<ClaimDetails> claimdetails) {
		this.claimdetails = claimdetails;
	}

	public VehicleDetails getVehicledetails() {
		return vehicledetails;
	}

	public void setVehicledetails(VehicleDetails vehicledetails) {
		this.vehicledetails = vehicledetails;
	}

	public VehicleInsurancePlan getVehicleinsuranceplan() {
		return vehicleinsuranceplan;
	}

	public void setVehicleinsuranceplan(VehicleInsurancePlan vehicleinsuranceplan) {
		this.vehicleinsuranceplan = vehicleinsuranceplan;
	}
	
	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "VehicleInsuranceDetails [insurancePolicyId=" + insurancePolicyId + ", insuranceDuration="
				+ insuranceDuration + ", insuranceStatus=" + insuranceStatus + ", insurancePremium=" + insurancePremium
				+ ", vehicledetails=" + vehicledetails + ", vehicleinsuranceplan=" + vehicleinsuranceplan + ", user="
				+ user + "]";
	}

	

	
}
