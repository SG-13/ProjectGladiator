package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lti.model.ClaimDetails;
import com.lti.model.TravelDetails;
import com.lti.model.TravelInsuranceDetails;
import com.lti.model.TravelInsurancePlan;
import com.lti.model.UserDetails;
import com.lti.model.VehicleDetails;
import com.lti.model.VehicleInsuranceDetails;
import com.lti.model.VehicleInsurancePlan;
import com.lti.service.ProjectService;

@Controller
public class ControllerImpl {

	@Autowired
	ProjectService service;
	
	public int addNewUser(UserDetails user) {
		return service.addNewUser(user);
	}

	public int isValidUser(int userId, String password) {
		return service.isValidUser(userId, password);
	}

	public int buyVehicleInsurance(int userId, String regno, int planId, VehicleInsuranceDetails vid) {
		return service.buyVehicleInsurance(userId, regno, planId, vid);
	}

	public int buyTravelInsurance(int userId, int travelId, int planId, TravelInsuranceDetails tid) {
		return service.buyTravelInsurance(userId, travelId, planId, tid);
	}

	public int renewInsurance(int policyId, int duration) {
		return service.renewInsurance(policyId, duration);
	}

	public String addVehicleDetails(VehicleDetails vehicle) {
		return service.addVehicleDetails(vehicle);
	}

	public int addTravelDetails(TravelDetails travel) {
		return service.addTravelDetails(travel);
	}
	
	public List<VehicleInsurancePlan> showVehicleInsurancePlan() {
		return service.showVehicleInsurancePlan();
	}

	public List<TravelInsurancePlan> showTravelInsurancePlan() {
		return service.showTravelInsurancePlan();
	}

	public int addVehicleClaimdetails(int policyId, ClaimDetails claimdetails) {
		return service.addVehicleClaimdetails(policyId, claimdetails);
	}

	public int addTravelClaimdetails(int policyId, ClaimDetails claimdetails) {
		return service.addTravelClaimdetails(policyId, claimdetails);
	}

	public boolean checkVehiclePolicyId(int policyId) {
		return service.checkVehiclePolicyId(policyId);
	}

	public boolean checkTravelPolicyId(int policyId) {
		return service.checkTravelPolicyId(policyId);
	}

	public ClaimDetails showClaimDetails(int claimId) {
		return service.showClaimDetails(claimId);
	}

	public List<ClaimDetails> showAllClaimDetails() {
		return service.showAllClaimDetails();
	}

	public List<TravelInsuranceDetails> showTravelInsuranceDetails() {
		return service.showTravelInsuranceDetails();
	}

	public List<VehicleInsuranceDetails> showVehicleInsuranceDetails() {
		return service.showVehicleInsuranceDetails();
	}

	public List<VehicleInsuranceDetails> showUserVehicleInsurance(int userId) {
		return service.showUserVehicleInsurance(userId);
	}

	public List<TravelInsuranceDetails> showUserTravelInsurance(int userId) {
		return service.showUserTravelInsurance(userId);
	}

	public void updateVehicleInsuranceStatus(int policyId, String status) {
		service.updateVehicleInsuranceStatus(policyId, status);

	}

	public void updateTravelInsuranceStatus(int policyId, String status) {
		service.updateTravelInsuranceStatus(policyId, status);

	}

	public void updateClaimStatus(int claimId, String status) {
		service.updateClaimStatus(claimId, status);

	}
	
	public int addVehicleInsurancePlan(VehicleInsurancePlan vip) {
		return service.addVehicleInsurancePlan(vip);
	}

	public int addTravelInsurancePlan(TravelInsurancePlan vip) {
		return service.addTravelInsurancePlan(vip);
	}
	
}
