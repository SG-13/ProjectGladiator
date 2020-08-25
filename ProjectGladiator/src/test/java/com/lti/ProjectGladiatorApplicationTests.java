package com.lti;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lti.controller.ControllerImpl;
import com.lti.model.ClaimDetails;
import com.lti.model.TravelDetails;
import com.lti.model.TravelInsuranceDetails;
import com.lti.model.TravelInsurancePlan;
import com.lti.model.UserDetails;
import com.lti.model.VehicleDetails;
import com.lti.model.VehicleInsuranceDetails;
import com.lti.model.VehicleInsurancePlan;
import com.lti.repo.RepositoryImpl;

@SpringBootTest
class ProjectGladiatorApplicationTests {

	@Autowired
	private ControllerImpl controller;
	
	
	@Test
	public void addUser() {
		UserDetails user = new UserDetails();
		
		user.setUserName("Yash Arora");
		user.setUserPassword("yash@1234");
		user.setUserEmail("yash@gmail.com");
		user.setUserAddress("Chandigarh, Haryana");
		user.setUserMobileNumber("3432890033");
		user.setUserDob(LocalDate.of(2003, 3, 21));
		
		System.out.println(controller.addNewUser(user));

	}
	
	
	@Test
	public void addvehicle() {
		
		VehicleDetails vd = new VehicleDetails();
		vd.setRegistrationNumber("RJ40SE1278");
		vd.setVehicleColour("Black");
		vd.setVehicleType("2 wheeler");
		vd.setVehicleModel("Activa");
		vd.setVehicleManufacturer("Honda");
		vd.setVehiclePurchaseDate(LocalDate.of(2019, 1, 05));
		vd.setVehicleEngineNumber("HO182");
		vd.setVehicleChassisNumber("98329HO");
		vd.setVehicleCost(54000);
		
		System.out.println(controller.addVehicleDetails(vd));
	}
	
	
	@Test
	public void addtravel() {
		
		TravelDetails td = new TravelDetails();
		td.setArrivalLocation("Dehradun");
		td.setDepartureLocation("Delhi");
		td.setStartDateOfJourney(LocalDate.of(2018, 03, 11));
		td.setEndDateOfJourney(LocalDate.of(2018, 03, 12));
		td.setModeOfTravel("Bus");
		td.setTravelCost(820);
		
		System.out.println(controller.addTravelDetails(td));
	}
	
	@Test
	public void isValidUser() {
		System.out.println(controller.isValidUser(1105, "dinesh@1234"));
	}

	@Test
	public void buyVehicleInsurance() {
		VehicleInsuranceDetails vid = new VehicleInsuranceDetails();
		vid.setInsuranceDuration(LocalDate.of(2023, 03, 20));
		vid.setInsurancePremium(4585.0);
		
		//System.out.println(controller.buyVehicleInsurance(1105, "RJ40CA0013",20204, vid));
	}
	
	
	@Test
	public void buyTravelInsurance() {
		RepositoryImpl repo = new RepositoryImpl();
		TravelInsuranceDetails tid = new TravelInsuranceDetails();
		tid.setInsuranceDuration(LocalDate.now());
		tid.setInsurancePremium(1200.0);
		
		System.out.println(repo.buyTravelInsurance(1105,30202,21202, tid));
	}
	
	@Test
	public void renewinsurance() {
		//System.out.println(controller.renewInsurance(50207, 2));
	}
	
	@Test
	public void showVehicleInsurancePlan() {
		System.out.println(controller.showVehicleInsurancePlan());
	}
	
	@Test
	public void showTravelInsurancePlan() {
		System.out.println(controller.showTravelInsurancePlan());
	}
	
	@Test
	public void checkVehiclePolicyId() {
		System.out.println(controller.checkVehiclePolicyId(50203));
	}
	
	@Test
	public void checkTravelPolicyId() {
		System.out.println(controller.checkTravelPolicyId(60203));
	}
	
	@Test
	public void addvehicleclaimdetails() {
		ClaimDetails cd = new ClaimDetails();
		
		cd.setClaimDate(LocalDate.now());
		cd.setDateOfIncident(LocalDate.now());
		cd.setReason("Theft");
		cd.setElaborateReason("Stolen from city plaza");
		
		System.out.println(controller.addVehicleClaimdetails(50206, cd));
	}
	
	@Test
	public void addtravelclaimdetails() {
		ClaimDetails cd = new ClaimDetails();
		
		cd.setClaimDate(LocalDate.now());
		cd.setDateOfIncident(LocalDate.of(2020, 8, 16));
		cd.setReason("ROAD Accident");
		cd.setElaborateReason("Bus crashed with streetlights");
		
		System.out.println(controller.addTravelClaimdetails(60203, cd));
	}
	
	@Test 
	public void showclaimDetails() {
		ClaimDetails cd = controller.showClaimDetails(70009);
		System.out.println(cd.getDateOfIncident());
	}
	
	@Test 
	public void showallclaimDetails() {
		List<ClaimDetails> list = controller.showAllClaimDetails();
		for(ClaimDetails c : list)
		{
			System.out.println(c.getElaborateReason());
		}
		
	}
	

	@Test 
	public void showuservehicleDetails() {
		List<VehicleInsuranceDetails> vidlist = controller.showUserVehicleInsurance(1109);
		for(VehicleInsuranceDetails vid : vidlist)
		{	//VehicleDetails vd = vid.getVehicledetails();
			System.out.println(vid.getInsurancePolicyId());
		}
	}
	
	@Test 
	public void showusertravelDetails() {
		List<TravelInsuranceDetails> tidlist = controller.showUserTravelInsurance(1107);
		for(TravelInsuranceDetails tid : tidlist) {
			System.out.println(tid.getInsuranceDuration());
		}
	}

	@Test
	public void showAllVehicleInsuranceDetails() {
		List<VehicleInsuranceDetails> vidlist = controller.showVehicleInsuranceDetails();
		for(VehicleInsuranceDetails vid : vidlist)
		{
			System.out.println(vid.getInsurancePolicyId());
		}
	}
	
	@Test
	public void showAllTravelInsuranceDetails() {
		List<TravelInsuranceDetails> tidlist = controller.showTravelInsuranceDetails();
		for(TravelInsuranceDetails tid : tidlist) {
			System.out.println(tid.getInsurancePolicyId());
		}
	}
	
	@Test
	public void updatevehiclestatus() {
	//controller.updateVehicleInsuranceStatus(50206, "Approved");
	}
	
	@Test
	public void updatetravelstatus() {
	//controller.updateTravelInsuranceStatus(60203, "Approved");
	}
	
	@Test
	public void updateclaimstatus() {
	//controller.updateClaimStatus(70006, "Approved");
	}
	
	
	@Test
	public void addVehicleInsurancePlan() {
		VehicleInsurancePlan vip = new VehicleInsurancePlan();
		vip.setInsurancePlan("Comprehensive Zero D");
		vip.setInsuranceCoverageAmount(100);
		
		System.out.println(controller.addVehicleInsurancePlan(vip));
		
	}
	
	@Test
	public void addTravelInsurancePlan() {
		TravelInsurancePlan tip = new TravelInsurancePlan();
		tip.setInsurancePlan("Silver");
		tip.setInsuranceCoverageAmount(1000000.0);
		
		System.out.println(controller.addTravelInsurancePlan(tip));
		
	}

}
