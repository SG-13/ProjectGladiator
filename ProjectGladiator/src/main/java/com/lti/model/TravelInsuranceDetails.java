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
public class TravelInsuranceDetails {

	@Id
	@SequenceGenerator(name = "seq_traInDe", initialValue = 60201, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_traInDe")
	private int insurancePolicyId;
	@Column
	private String insuranceStatus;
	@Column
	private int insuranceDuration;
	@Column
	private Double insurancePremium;
	
	@OneToOne(mappedBy = "travelinsurancedetails", cascade=CascadeType.ALL)
	private ClaimDetails claimdetails;
	
	@OneToOne
	@JoinColumn(name = "travel_id")
	private TravelDetails traveldetails;
	
	@OneToOne
	@JoinColumn(name = "insurance_planId")
	private TravelInsurancePlan travelinsuranceplan;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserDetails user;

	
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

	public int getInsuranceDuration() {
		return insuranceDuration;
	}

	public void setInsuranceDuration(int insuranceDuration) {
		this.insuranceDuration = insuranceDuration;
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
	
	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TravelInsuranceDetails [insurancePolicyId=" + insurancePolicyId + ", insuranceStatus=" + insuranceStatus
				+ ", insuranceDuration=" + insuranceDuration + ", insurancePremium=" + insurancePremium
				+ ", traveldetails=" + traveldetails + ", travelinsuranceplan=" + travelinsuranceplan + ", user=" + user
				+ "]";
	}

	
}
