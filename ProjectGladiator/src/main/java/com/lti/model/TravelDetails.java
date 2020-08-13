package com.lti.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class TravelDetails {

	@Id
	@SequenceGenerator(name = "seq_traDe", initialValue = 30201, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_traDe")
	private int travelId;
	@Column
	private LocalDate startDateOfJourney;
	@Column
	private LocalDate endDateOfJourney;
	@Column
	private String departureLocation;
	@Column
	private String arrivalLocation;
	@Column
	private String modeOfTravel;
	@Column
	private Double TravelCost;
	
	@OneToOne(mappedBy = "traveldetails", cascade=CascadeType.ALL)
	private TravelInsuranceDetails travelinsurancedetails;

	
	public int getTravelId() {
		return travelId;
	}

	public void setTravelId(int travelId) {
		this.travelId = travelId;
	}

	public LocalDate getStartDateOfJourney() {
		return startDateOfJourney;
	}

	public void setStartDateOfJourney(LocalDate startDateOfJourney) {
		this.startDateOfJourney = startDateOfJourney;
	}

	public LocalDate getEndDateOfJourney() {
		return endDateOfJourney;
	}

	public void setEndDateOfJourney(LocalDate endDateOfJourney) {
		this.endDateOfJourney = endDateOfJourney;
	}

	public String getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public String getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(String arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public String getModeOfTravel() {
		return modeOfTravel;
	}

	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
	}

	public Double getTravelCost() {
		return TravelCost;
	}

	public void setTravelCost(Double travelCost) {
		TravelCost = travelCost;
	}

	public TravelInsuranceDetails getTravelinsurancedetails() {
		return travelinsurancedetails;
	}

	public void setTravelinsurancedetails(TravelInsuranceDetails travelinsurancedetails) {
		this.travelinsurancedetails = travelinsurancedetails;
	}

	
	@Override
	public String toString() {
		return "TravelDetails [travelId=" + travelId + ", startDateOfJourney=" + startDateOfJourney
				+ ", endDateOfJourney=" + endDateOfJourney + ", departureLocation=" + departureLocation
				+ ", arrivalLocation=" + arrivalLocation + ", modeOfTravel=" + modeOfTravel + ", TravelCost="
				+ TravelCost + "]";
	}
	
	
	
}
