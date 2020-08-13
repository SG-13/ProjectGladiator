package com.lti.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class TravelInsuranceDetails {

	@Id
	@SequenceGenerator(name = "seq_traInDe", initialValue = 60201, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_traInDe")
	private int insurancePolicyId;
	@Column
	private String insuranceStatus;
	@Column
	private Double insurancePremium;
	@Column
	private String userName;
	@Column
	private String userEmail;
	@Column
	private LocalDate userDob;
	@Column
	private String userMobileNumber;
	@Column
	private String userAddress;
	@Column
	private String userPassword;
	
	@OneToOne(mappedBy = "travelinsurancedetails", cascade=CascadeType.ALL)
	private ClaimDetails claimdetails;
	
	@OneToOne
	@JoinColumn(name = "travel_id")
	private TravelDetails traveldetails;
	
	@OneToOne
	@JoinColumn(name = "insurance_planId")
	private TravelInsurancePlan travelinsuranceplan;

	public int getInsurancePolicyId() {
		return insurancePolicyId;
	}

	public void setInsurancePolicyId(int insurancePolicyId) {
		this.insurancePolicyId = insurancePolicyId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LocalDate getUserDob() {
		return userDob;
	}

	public void setUserDob(LocalDate userDob) {
		this.userDob = userDob;
	}

	public String getUserMobileNumber() {
		return userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public ClaimDetails getClaimdetails() {
		return claimdetails;
	}

	public void setClaimdetails(ClaimDetails claimdetails) {
		this.claimdetails = claimdetails;
	}

	public TravelDetails getTraveldetails() {
		return traveldetails;
	}

	public void setTraveldetails(TravelDetails traveldetails) {
		this.traveldetails = traveldetails;
	}

	public TravelInsurancePlan getTravelinsuranceplan() {
		return travelinsuranceplan;
	}

	public void setTravelinsuranceplan(TravelInsurancePlan travelinsuranceplan) {
		this.travelinsuranceplan = travelinsuranceplan;
	}

	
	@Override
	public String toString() {
		return "TravelInsuranceDetails [insurancePolicyId=" + insurancePolicyId + ", insuranceStatus=" + insuranceStatus
				+ ", insurancePremium=" + insurancePremium + ", userName=" + userName + ", userEmail=" + userEmail
				+ ", userDob=" + userDob + ", userMobileNumber=" + userMobileNumber + ", userAddress=" + userAddress
				+ ", userPassword=" + userPassword + ", traveldetails=" + traveldetails + ", travelinsuranceplan="
				+ travelinsuranceplan + "]";
	}
	
	
}
