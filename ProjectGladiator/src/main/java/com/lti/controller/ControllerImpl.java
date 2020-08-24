package com.lti.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.controller.ControllerImpl.Status.StatusType;
import com.lti.dto.BuyTravelInsuranceDto;
import com.lti.dto.BuyVehicleInsuranceDto;
import com.lti.dto.LoginDto;
import com.lti.dto.SendId;
import com.lti.dto.SendVehicleId;
import com.lti.dto.UserDto;
import com.lti.exception.ServiceException;
import com.lti.model.ClaimDetails;
import com.lti.model.TravelDetails;
import com.lti.model.TravelInsuranceDetails;
import com.lti.model.TravelInsurancePlan;
import com.lti.model.UserDetails;
import com.lti.model.VehicleDetails;
import com.lti.model.VehicleInsuranceDetails;
import com.lti.model.VehicleInsurancePlan;
import com.lti.service.ProjectService;

@RestController
@CrossOrigin
public class ControllerImpl {

	@Autowired
	ProjectService service;
	
	@Autowired
	private MailSender mailSender;
	
	public int addNewUser(UserDetails user) {
		return service.addNewUser(user);
	}

	public int isValidUser(int userId, String password) {
		return service.isValidUser(userId, password);
	}
	
	@RequestMapping(path = "/buyVehicleInsurance", method = RequestMethod.POST)
	public SendId buyVehicleInsurance(@RequestBody BuyVehicleInsuranceDto buyvehicleinsurancedto) {
		//System.out.println(buyvehicleinsurancedto.getUserId()+" "+ buyvehicleinsurancedto.getRegistrationNumber());
		SendId sendId = new SendId();
		VehicleInsuranceDetails vid = new VehicleInsuranceDetails();
		vid.setInsurancePremium(buyvehicleinsurancedto.getInsurancePremium());
		LocalDate date = LocalDate.now().minusDays(1);
		vid.setInsuranceDuration(date.plusYears(buyvehicleinsurancedto.getDuration()));
		sendId.setId(service.buyVehicleInsurance(buyvehicleinsurancedto.getUserId(), buyvehicleinsurancedto.getRegistrationNumber(), buyvehicleinsurancedto.getInsurancePlanId(), vid));
		return sendId;
	}
	
	@RequestMapping(path = "/buyTravelInsurance", method = RequestMethod.POST)
	public SendId buyTravelInsurance(@RequestBody BuyTravelInsuranceDto buytravelinsurancedto) {
		//System.out.println(buytravelinsurancedto.getTravelinsurancedetails());
		SendId sendId = new SendId();
		TravelInsuranceDetails tid = new TravelInsuranceDetails();
		tid.setInsurancePremium(buytravelinsurancedto.getInsurancePremium());
		tid.setInsuranceDuration(buytravelinsurancedto.getInsuranceDuration());
		sendId.setId(service.buyTravelInsurance(buytravelinsurancedto.getUserId(), buytravelinsurancedto.getTravelId(), buytravelinsurancedto.getInsurancePlanId(), tid));
		return sendId;
	}

	public int renewInsurance(int policyId, int duration) {
		return service.renewInsurance(policyId, duration);
	}
	
	@RequestMapping(path = "/addVehicleDetails", method = RequestMethod.POST)
	public SendVehicleId addVehicleDetails(@RequestBody VehicleDetails vehicle) {
		//System.out.println(vehicle.getRegistrationNumber());
		SendVehicleId vehicleId = new SendVehicleId();
		vehicleId.setVehicleId(service.addVehicleDetails(vehicle));
		return vehicleId;
		
	}
	
	@RequestMapping(path = "/addTravelDetails", method = RequestMethod.POST)
	public SendId addTravelDetails(@RequestBody TravelDetails travel) {
		SendId sendId = new SendId(); 
		sendId.setId(service.addTravelDetails(travel));
		return sendId;
	}
	
	@RequestMapping(path = "/showVehicleInsurancePlan", method = RequestMethod.GET)
	public List<VehicleInsurancePlan> showVehicleInsurancePlan() {
		return service.showVehicleInsurancePlan();
	}

	@RequestMapping(path = "/showTravelInsurancePlan", method = RequestMethod.GET)
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

	@RequestMapping(path = "/showAllClaimDetails", method = RequestMethod.GET)
	public List<ClaimDetails> showAllClaimDetails() {
		return service.showAllClaimDetails();
	}

	@RequestMapping(path = "/showTravelInsuranceDetails", method = RequestMethod.GET)
	public List<TravelInsuranceDetails> showTravelInsuranceDetails() {
		return service.showTravelInsuranceDetails();
	}
	@RequestMapping(path = "/showVehicleInsuranceDetails", method = RequestMethod.GET)
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
	
	@PostMapping(path="/register")
	public Status register(@RequestBody UserDto userDto ) 
	{
		
		try 
		{
			UserDetails userDetails=new UserDetails() ;
			BeanUtils.copyProperties(userDto, userDetails);
			
		    int id=service.register(userDetails);
			Status status= new Status();
			status.setUserId(id);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration successfull");
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("sharmautkarsh815@outlook.com");
			message.setTo(userDetails.getUserEmail());
			message.setSubject("Registration Successfull");
			message.setText("Dear "+userDetails.getUserName()+"!"+"\n\n"+"You are successfully registered."+"\n"+"This is your user Id "+id+". Use it for the future refrences."+"\n\n"+"Have a good day."+"\n"+"LTI Insurance");
			mailSender.send(message);
			return status;
			
		} 
		catch (ServiceException e) 
		{
			Status status=new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
		
			return status;
		}
			
		
	}
	@PostMapping(path="/login")
    public LoginStatus login(@RequestBody LoginDto loginDto) {
    	try {
    		//UserDetails userDetails=service.login(loginDto.getUserEmail(), loginDto.getUserPassword());
		    System.out.println(loginDto.getUserPassword());
    		UserDetails userDetails=service.login(loginDto.getUserId(), loginDto.getUserPassword());
    		LoginStatus loginStatus=new LoginStatus();
    		loginStatus.setStatus(StatusType.SUCCESS);
    		loginStatus.setUserId(userDetails.getUserId());
    		loginStatus.setUserName(userDetails.getUserName());
    		loginStatus.setMessage("You are successfully logged in!");
    		return loginStatus;
    		}catch(ServiceException e) {
    			LoginStatus loginStatus=new LoginStatus();
        		loginStatus.setStatus(StatusType.FAILURE);
        		loginStatus.setMessage(e.getMessage());
        		
        		return loginStatus;
    		}
    }

	
	public static class LoginStatus extends Status{
    	private int userId;
    	
    	private String userName;

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
		
    	
    	
    }
	
	public static class Status 
	{
		private int userId;
		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		private StatusType status;
		private String message;
		
		public static enum StatusType {
			SUCCESS, FAILURE;
		}
		
		public StatusType getStatus() {
			return status;
		}

		public void setStatus(StatusType status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		
		
		
	}
	
	
}
