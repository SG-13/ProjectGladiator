package com.lti.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

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
	private String Status;
	@Column
	private String Reason;
	@Column
	private String elaborateReason;
	@Column
	private int Double;
	
	@OneToOne
	@JoinColumn(name = "t_insurance_policyid")
	private TravelInsuranceDetails travelinsurancedetails;
	
	@OneToOne
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
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getElaborateReason() {
		return elaborateReason;
	}

	public void setElaborateReason(String elaborateReason) {
		this.elaborateReason = elaborateReason;
	}

	public int getDouble() {
		return Double;
	}

	public void setDouble(int d) {
		Double = d;
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

	@Override
	public String toString() {
		return "ClaimDetails [claimId=" + claimId + ", dateOfIncident=" + dateOfIncident + ", claimDate=" + claimDate
				+ ", Status=" + Status + ", Reason=" + Reason + ", elaborateReason=" + elaborateReason + ", Double="
				+ Double + ", travelinsurancedetails=" + travelinsurancedetails + ", vehicleinsurancedetails="
				+ vehicleinsurancedetails + "]";
	}
	

}