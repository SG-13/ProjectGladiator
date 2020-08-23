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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TravelInsuranceDetails {

	@Id
	@SequenceGenerator(name = "seq_traInDe", initialValue = 60201, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_traInDe")
	private int insurancePolicyId;
	@Column
	private String insuranceStatus;
	@Column
	private LocalDate insuranceDuration;
	@Column
	private double insurancePremium;
	
	@JsonIgnore
	@OneToMany(mappedBy = "travelinsurancedetails", cascade=CascadeType.ALL)
	private List<ClaimDetails> claimdetails;
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "travel_id")
	private TravelDetails traveldetails;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insurance_planId")
	private TravelInsurancePlan travelinsuranceplan;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
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

	public LocalDate getInsuranceDuration() {
		return insuranceDuration;
	}

	public void setInsuranceDuration(LocalDate insuranceDuration) {
		this.insuranceDuration = insuranceDuration;
	}

	public double getInsurancePremium() {
		return insurancePremium;
	}

	public void setInsurancePremium(double insurancePremium) {
		this.insurancePremium = insurancePremium;
	}

	public List<ClaimDetails> getClaimdetails() {
		return claimdetails;
	}

	public void setClaimdetails(List<ClaimDetails> claimdetails) {
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


}
