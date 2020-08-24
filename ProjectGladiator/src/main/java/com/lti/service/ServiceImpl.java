package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.exception.ServiceException;
import com.lti.function.Calculate;
import com.lti.model.ClaimDetails;
import com.lti.model.TravelDetails;
import com.lti.model.TravelInsuranceDetails;
import com.lti.model.TravelInsurancePlan;
import com.lti.model.UserDetails;
import com.lti.model.VehicleDetails;
import com.lti.model.VehicleInsuranceDetails;
import com.lti.model.VehicleInsurancePlan;
import com.lti.repo.ProjectRepository;

@Service
public class ServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository repo;
	
	public int addNewUser(UserDetails user) {
		return repo.addNewUser(user);
	}

	public int isValidUser(int userId, String password) {
		return repo.isValidUser(userId, password);
	}

	public int buyVehicleInsurance(int userId, String regno, int planId, VehicleInsuranceDetails vid) {
		return repo.buyVehicleInsurance(userId, regno, planId, vid);
	}

	public int buyTravelInsurance(int userId, int travelId, int planId, TravelInsuranceDetails tid) {
		return repo.buyTravelInsurance(userId, travelId, planId, tid);
	}

	public int renewInsurance(int policyId, int duration) {
		return repo.renewInsurance(policyId, duration);
	}

	public String addVehicleDetails(VehicleDetails vehicle) {
		return repo.addVehicleDetails(vehicle);
	}
	
	public int addTravelDetails(TravelDetails travel) {
		return repo.addTravelDetails(travel);
	}

	public List<VehicleInsurancePlan> showVehicleInsurancePlan() {
		return repo.showVehicleInsurancePlan();
	}

	public List<TravelInsurancePlan> showTravelInsurancePlan() {
		return repo.showTravelInsurancePlan();
	}

	public int addVehicleClaimdetails(int policyId, ClaimDetails claimdetails) {
		return repo.addVehicleClaimdetails(policyId, claimdetails);
	}

	public int addTravelClaimdetails(int policyId, ClaimDetails claimdetails) {
		return repo.addTravelClaimdetails(policyId, claimdetails);
	}

	public boolean checkVehiclePolicyId(int policyId) {
		return repo.checkVehiclePolicyId(policyId);
	}

	public boolean checkTravelPolicyId(int policyId) {
		return repo.checkTravelPolicyId(policyId);
	}

	public ClaimDetails showClaimDetails(int claimId) {
		return repo.showClaimDetails(claimId);
	}

	public List<ClaimDetails> showAllClaimDetails() {
		return repo.showAllClaimDetails();
	}

	public List<TravelInsuranceDetails> showTravelInsuranceDetails() {
		return repo.showTravelInsuranceDetails();
	}

	public List<VehicleInsuranceDetails> showVehicleInsuranceDetails() {
		return repo.showVehicleInsuranceDetails();
	}

	public List<VehicleInsuranceDetails> showUserVehicleInsurance(int userId) {
		return repo.showUserVehicleInsurance(userId);
	}

	public List<TravelInsuranceDetails> showUserTravelInsurance(int userId) {
		return repo.showUserTravelInsurance(userId);
	}

	public void updateVehicleInsuranceStatus(int policyId, String status) {
		repo.updateVehicleInsuranceStatus(policyId, status);

	}

	public void updateTravelInsuranceStatus(int policyId, String status) {
		repo.updateTravelInsuranceStatus(policyId, status);

	}

	public void updateClaimStatus(int claimId, String status) {
		repo.updateClaimStatus(claimId, status);

	}

	public int addVehicleInsurancePlan(VehicleInsurancePlan vip) {
		return repo.addVehicleInsurancePlan(vip);
	}

	public int addTravelInsurancePlan(TravelInsurancePlan vip) {
		return repo.addTravelInsurancePlan(vip);
	}

	@Override
	public int register(UserDetails userDetails) 
	{
		if(!repo.findByEmail(userDetails.getUserEmail()))
		  {
			return repo.addNewUser(userDetails);
			
		  }
		else
			throw new ServiceException("User already registerd");
	}
	
	@Override
	public UserDetails login(int userId, String password) {
		
		try {
			
			int id=repo.findByIdAndPassword(userId, password);
			UserDetails userDetails=repo.findById(id);
			System.out.println(userDetails.getUserId());
			return userDetails;
			
		} catch( EmptyResultDataAccessException e) {
			throw new ServiceException("Incorrect userId/Password");
		}
	} 

	
	@Override
	public boolean checkPolicyId(int policyId) {
		return repo.checkPolicyId(policyId);
	}

	@Override
	public int addClaim(int policyId, ClaimDetails claimDetails) {
		int pid=policyId;
		Calculate calc=new Calculate();
		int n=calc.firstDigit(pid);
		if(n==5) {
		return repo.addVehicleClaimdetails(policyId, claimDetails);
		}
		else if(n==6) {
		return repo.addTravelClaimdetails(policyId, claimDetails);
		}
		return 0;

	}
	@Override
	public List<ClaimDetails> getAllClaim(int userId){
		return repo.getAllClaim(userId);
	}
	

}
