package com.lti.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VehicleDetails {

	@Id
	private String registrationNumber;
	@Column
	private String vehicleColour;
	@Column
	private String vehicleType;
	@Column
	private String vehicleModel;
	@Column
	private String vehicleManufacturer;
	@Column
	private LocalDate vehiclePurchaseDate;
	@Column
	private String vehicleEngineNumber;
	@Column
	private String vehicleChassisNumber;
	@Column
	private Double vehicleCost;
	@JsonIgnore
	@OneToOne(mappedBy = "vehicledetails", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private VehicleInsuranceDetails vehicleinsurancedetails;

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getVehicleColour() {
		return vehicleColour;
	}

	public void setVehicleColour(String vehicleColour) {
		this.vehicleColour = vehicleColour;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleManufacturer() {
		return vehicleManufacturer;
	}

	public void setVehicleManufacturer(String vehicleManufacturer) {
		this.vehicleManufacturer = vehicleManufacturer;
	}

	public LocalDate getVehiclePurchaseDate() {
		return vehiclePurchaseDate;
	}

	public void setVehiclePurchaseDate(LocalDate vehiclePurchaseDate) {
		this.vehiclePurchaseDate = vehiclePurchaseDate;
	}

	public String getVehicleEngineNumber() {
		return vehicleEngineNumber;
	}

	public void setVehicleEngineNumber(String vehicleEngineNumber) {
		this.vehicleEngineNumber = vehicleEngineNumber;
	}

	public String getVehicleChassisNumber() {
		return vehicleChassisNumber;
	}

	public void setVehicleChassisNumber(String vehicleChassisNumber) {
		this.vehicleChassisNumber = vehicleChassisNumber;
	}

	public double getVehicleCost() {
		return vehicleCost;
	}

	public void setVehicleCost(double vehicleCost) {
		this.vehicleCost = vehicleCost;
	}

	public VehicleInsuranceDetails getVehicleinsurancedetails() {
		return vehicleinsurancedetails;
	}

	public void setVehicleinsurancedetails(VehicleInsuranceDetails vehicleinsurancedetails) {
		this.vehicleinsurancedetails = vehicleinsurancedetails;
	}
	
}
