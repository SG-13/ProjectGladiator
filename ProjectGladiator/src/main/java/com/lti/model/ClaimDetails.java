package com.lti.model;

import java.time.LocalDate;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ClaimDetails {

	@Id
	@SequenceGenerator(name = "seq_claDe", initialValue = 70001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_claDe")
	private int claimId;
	@Column
	private LocalDate dateOfIncident;
	@Column
	private LocalDate claimDate;
	@Column
	private String status;
	@Column
	private String reason;
	@Column
	private String elaborateReason;
	@Column
	private double claimAmount;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_insurance_policyid")
	private TravelInsuranceDetails travelinsurancedetails;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "v_insurance_policyid")
	private VehicleInsuranceDetails vehicleinsurancedetails;

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public LocalDate getDateOfIncident() {
		return dateOfIncident;
	}

	public void setDateOfIncident(LocalDate dateOfIncident) {
		this.dateOfIncident = dateOfIncident;
	}

	public LocalDate getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getElaborateReason() {
		return elaborateReason;
	}

	public void setElaborateReason(String elaborateReason) {
		this.elaborateReason = elaborateReason;
	}

	
	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public TravelInsuranceDetails getTravelinsurancedetails() {
		return travelinsurancedetails;
	}

	public void setTravelinsurancedetails(TravelInsuranceDetails travelinsurancedetails) {
		this.travelinsurancedetails = travelinsurancedetails;
	}

	public VehicleInsuranceDetails getVehicleinsurancedetails() {
		return vehicleinsurancedetails;
	}

	public void setVehicleinsurancedetails(VehicleInsuranceDetails vehicleinsurancedetails) {
		this.vehicleinsurancedetails = vehicleinsurancedetails;
	}

	

}
