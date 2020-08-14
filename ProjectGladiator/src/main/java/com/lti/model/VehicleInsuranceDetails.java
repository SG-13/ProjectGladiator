package com.lti.model;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class VehicleInsuranceDetails {

	@Id
	@SequenceGenerator(name = "seq_vecInDe", initialValue = 50201, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vecInDe")
	private int insurancePolicyId;
	@Column
	private int insuranceDuration;
	@Column
	private String insuranceStatus;
	@Column
	private Double insurancePremium;
	
	@OneToOne(mappedBy = "vehicleinsurancedetails", cascade=CascadeType.ALL)
	private ClaimDetails claimdetails;
	
	@OneToOne
	@JoinColumn(name = "vehicle_regNo")
	private VehicleDetails vehicledetails;
	
	@OneToOne
	@JoinColumn(name = "insurance_planId")
	private VehicleInsurancePlan vehicleinsuranceplan;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserDetails user;
	
	

	public int getInsurancePolicyId() {
		return insurancePolicyId;
	}

	public void setInsurancePolicyId(int insurancePolicyId) {
		this.insurancePolicyId = insurancePolicyId;
	}

	public int getInsuranceDuration() {
		return insuranceDuration;
	}

	public void setInsuranceDuration(int insuranceDuration) {
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

	public ClaimDetails getClaimdetails() {
		return claimdetails;
	}

	public void setClaimdetails(ClaimDetails claimdetails) {
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
