package com.lti.service;

import java.time.LocalDate;
import java.util.List;

import com.lti.dto.AllVehiclePolicies;
import com.lti.model.ClaimDetails;
import com.lti.model.TravelDetails;
import com.lti.model.TravelInsuranceDetails;
import com.lti.model.TravelInsurancePlan;
import com.lti.model.UserDetails;
import com.lti.model.VehicleDetails;
import com.lti.model.VehicleInsuranceDetails;
import com.lti.model.VehicleInsurancePlan;

public interface ProjectService {

	int addNewUser(UserDetails user);

	int isValidUser(int userId, String password);
	
	boolean checkIfVehicleExist(String registrationNumber);

	int buyVehicleInsurance(int userId, String regno, int planId, VehicleInsuranceDetails vid);

	int buyTravelInsurance(int userId, int travelId, int planId, TravelInsuranceDetails tid);

	String addVehicleDetails(VehicleDetails vehicle);

	public int addTravelDetails(TravelDetails travel);

	List<VehicleInsurancePlan> showVehicleInsurancePlan();

	List<TravelInsurancePlan> showTravelInsurancePlan();

	int addVehicleClaimdetails(int policyId, ClaimDetails claimdetails);

	int addTravelClaimdetails(int policyId, ClaimDetails claimdetails);

	boolean checkVehiclePolicyId(int policyId);

	boolean checkTravelPolicyId(int policyId);

	ClaimDetails showClaimDetails(int claimId);

	List<ClaimDetails> showAllClaimDetails();

	List<TravelInsuranceDetails> showTravelInsuranceDetails();

	List<VehicleInsuranceDetails> showVehicleInsuranceDetails();

	List<VehicleInsuranceDetails> showUserVehicleInsurance(int userId);

	List<TravelInsuranceDetails> showUserTravelInsurance(int userId);

	void updateVehicleInsuranceStatus(int policyId, String status);

	void updateTravelInsuranceStatus(int policyId, String status);

	void updateClaimStatus(int claimId, double claimAmount, String status);

	int addVehicleInsurancePlan(VehicleInsurancePlan vip);

	int addTravelInsurancePlan(TravelInsurancePlan vip);

	int register(UserDetails userDetails);

	UserDetails login(int userId, String password);

	boolean checkPolicyId(int policyId);// add new

	int addClaim(int policyId, ClaimDetails claimDetails);

	public List<ClaimDetails> getAllClaim(int userId);

	VehicleInsuranceDetails renewInsurance(int insurancePolicyId, int insuranceDuration);

	String findUserByPolicyId(int insurancePolicyId);

	String findInsuranceByPolicyId(int insurancePolicyId);

	UserDetails findUserById(int userId);

	public List<Object> getAllVehiclePolicies();

	List<Object> getAllTravelPolicies();

	public UserDetails findUserByVehiclePolicyId(int insurancePolicyId);

	public int findByEmailforOTP(String userEmail);

	public boolean forgotPassword(String userEmail, String newPassword);

	public List<Object> getAllVehiclePoliciesByUserId(int userId);

	public List<Object> getAllTravelPoliciesByUserId(int userId);

	long checkVehicleClaimOnBasisOfStatus(int policyId);
	
	long checkTravelClaimOnBasisOfStatus(int policyId);

	boolean findPolicyIdByUserId(int insurancePolicyId, int userId);
	
	boolean checkIfRenewable(int insurancePolicyId);
	
	double getPolicyPremiumfromPolicyId(int insurancePolicyId);
}
