package com.lti.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.controller.ControllerImpl.Status.StatusType;
import com.lti.controller.ControllerImpl.Status1.StatusType1;
import com.lti.dto.AllVehiclePolicies;
import com.lti.dto.BuyTravelInsuranceDto;
import com.lti.dto.BuyVehicleInsuranceDto;
import com.lti.dto.CheckDto;
import com.lti.dto.ClaimDto;
import com.lti.dto.DashDto;
import com.lti.dto.ForgotPasswordDto;
import com.lti.dto.LoginDto;
import com.lti.dto.NewClaimStatusDto;
import com.lti.dto.NewInsuranceStatusDto;
import com.lti.dto.PolicyIdObject;
import com.lti.dto.RenewObject;
import com.lti.dto.SendId;
import com.lti.dto.SendVehicleId;
import com.lti.dto.ShowDto;
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
import com.lti.utility.EmailUtility;

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
		// System.out.println(buyvehicleinsurancedto.getUserId()+" "+
		// buyvehicleinsurancedto.getRegistrationNumber());
		SendId sendId = new SendId();
		VehicleInsuranceDetails vid = new VehicleInsuranceDetails();
		vid.setInsurancePremium(buyvehicleinsurancedto.getInsurancePremium());
		LocalDate date = LocalDate.now().minusDays(1);
		vid.setInsuranceDuration(date.plusYears(buyvehicleinsurancedto.getDuration()));
		vid.setInsuranceStatus("Pending");
		UserDetails user = service.findUserById(buyvehicleinsurancedto.getUserId());

		sendId.setId(service.buyVehicleInsurance(buyvehicleinsurancedto.getUserId(),
				buyvehicleinsurancedto.getRegistrationNumber(), buyvehicleinsurancedto.getInsurancePlanId(), vid));
		EmailUtility eutility = new EmailUtility();
		eutility.BuyVehicleInsuraceEmail(mailSender, buyvehicleinsurancedto, user, sendId.getId());

		return sendId;
	}

	@RequestMapping(path = "/buyTravelInsurance", method = RequestMethod.POST)
	public SendId buyTravelInsurance(@RequestBody BuyTravelInsuranceDto buytravelinsurancedto) {
		// System.out.println(buytravelinsurancedto.getTravelinsurancedetails());
		SendId sendId = new SendId();
		TravelInsuranceDetails tid = new TravelInsuranceDetails();
		tid.setInsurancePremium(buytravelinsurancedto.getInsurancePremium());
		tid.setInsuranceDuration(buytravelinsurancedto.getInsuranceDuration());
		tid.setInsuranceStatus("Pending");
		UserDetails user = service.findUserById(buytravelinsurancedto.getUserId());

		sendId.setId(service.buyTravelInsurance(buytravelinsurancedto.getUserId(), buytravelinsurancedto.getTravelId(),
				buytravelinsurancedto.getInsurancePlanId(), tid));
		EmailUtility eutility = new EmailUtility();
		eutility.BuyTravelInsuraceEmail(mailSender, buytravelinsurancedto, user, sendId.getId());
		return sendId;
	}

	@RequestMapping(path = "/addVehicleDetails", method = RequestMethod.POST)
	public SendVehicleId addVehicleDetails(@RequestBody VehicleDetails vehicle) {
		// System.out.println(vehicle.getRegistrationNumber());
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

	@PostMapping(path = "/updateVehicleInsuranceStatus")
	public void updateVehicleInsuranceStatus(@RequestBody NewInsuranceStatusDto dto) {
		service.updateVehicleInsuranceStatus(dto.getInsurancePolicyId(), dto.getStatus());

	}

	@PostMapping(path = "/updateTravelInsuranceStatus")
	public void updateTravelInsuranceStatus(@RequestBody NewInsuranceStatusDto dto) {
		service.updateTravelInsuranceStatus(dto.getInsurancePolicyId(), dto.getStatus());
	}

	@PostMapping(path = "/updateClaimStatus")
	public void updateClaimStatus(@RequestBody NewClaimStatusDto dto) {
		service.updateClaimStatus(dto.getClaimId(), dto.getClaimAmount(), dto.getStatus());

	}

	public int addVehicleInsurancePlan(VehicleInsurancePlan vip) {
		return service.addVehicleInsurancePlan(vip);
	}

	public int addTravelInsurancePlan(TravelInsurancePlan vip) {
		return service.addTravelInsurancePlan(vip);
	}

	@PostMapping(path = "/register")
	public Status register(@RequestBody UserDto userDto) {

		try {
			UserDetails userDetails = new UserDetails();
			BeanUtils.copyProperties(userDto, userDetails);

			int id = service.register(userDetails);
			Status status = new Status();
			status.setUserId(id);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration successfull");
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("sharmautkarsh815@outlook.com");
			message.setTo(userDetails.getUserEmail());
			message.setSubject("Registration Successfull");
			message.setText("Dear " + userDetails.getUserName() + "!" + "\n\n" + "You are successfully registered."
					+ "\n" + "This is your user Id " + id + ". Use it for the future refrences." + "\n\n"
					+ "Have a good day." + "\n" + "LTI Insurance");
			mailSender.send(message);
			return status;

		} catch (ServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());

			return status;
		}

	}

	@PostMapping(path = "/login")
	public LoginStatus login(@RequestBody LoginDto loginDto) {
		try {
			// UserDetails userDetails=service.login(loginDto.getUserEmail(),
			// loginDto.getUserPassword());
			System.out.println(loginDto.getUserPassword());
			UserDetails userDetails = service.login(loginDto.getUserId(), loginDto.getUserPassword());
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(StatusType.SUCCESS);
			loginStatus.setUserId(userDetails.getUserId());
			loginStatus.setUserName(userDetails.getUserName());
			loginStatus.setMessage("You are successfully logged in!");
			return loginStatus;
		} catch (ServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());

			return loginStatus;
		}
	}

	public static class LoginStatus extends Status {
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

	public static class Status {
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

	@PostMapping(path = "/checkPolicyIdForUserId")
	public Status1 checkPolicyIdForUserId(@RequestBody ClaimDto claimdto) {
		List<VehicleInsuranceDetails> vid = service.showUserVehicleInsurance(claimdto.getUserId());
		System.out.println(claimdto.getUserId() + " " + claimdto.getPolicyId());
		for (VehicleInsuranceDetails details : vid) {
			if (details.getInsurancePolicyId() == claimdto.getPolicyId()) {
				long d = service.checkVehicleClaimOnBasisOfStatus(claimdto.getPolicyId());
				if (d >= 1) {
					Status1 status = new Status1();
					status.setStatus(StatusType1.Failure);
					status.setMessage("Claim Already registered");
					return status;
				}
				Status1 status = new Status1();
				status.setStatus(StatusType1.Success);
				status.setMessage("OK");
				return status;
			}
		}
		List<TravelInsuranceDetails> tid = service.showUserTravelInsurance(claimdto.getUserId());
		for (TravelInsuranceDetails trvdetails : tid) {
			System.out.println(claimdto.getPolicyId());
			if (trvdetails.getInsurancePolicyId() == claimdto.getPolicyId()) {
				long t = service.checkTravelClaimOnBasisOfStatus(claimdto.getPolicyId());
				if(t>=1) {
					 Status1 status=new Status1();
					status.setStatus(StatusType1.Failure);
					status.setMessage("Claim Already registered");
					return status;
				 }
				Status1 status = new Status1();
				status.setStatus(StatusType1.Success);
				status.setMessage("OK");
				return status;
			}
		}
		Status1 status = new Status1();
		status.setStatus(StatusType1.Failure);
		status.setMessage("This Policy does not exist for this User");
		return status;
	}

	public static class Status1 {

		private StatusType1 status;
		private String message;
		int claimId;

		public int getClaimId() {
			return claimId;
		}

		public void setClaimId(int claimId) {
			this.claimId = claimId;
		}

		public static enum StatusType1 {
			Success, Failure
		}

		public StatusType1 getStatus() {
			return status;
		}

		public void setStatus(StatusType1 status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	@PostMapping(path = "/addClaimForUser")
	public Status1 addClaimForUser(@RequestBody ClaimDto claimDto) {

		System.out.println(claimDto.getReason());
		System.out.println(claimDto.getPolicyId());
		System.out.println(claimDto.getUserId());
		ClaimDetails claimDetails = new ClaimDetails();
		claimDetails.setDateOfIncident(claimDto.getDateOfIncident());
		claimDetails.setClaimDate(claimDto.getClaimDate());
		claimDetails.setReason(claimDto.getReason());
		claimDetails.setElaborateReason(claimDto.getElaborateReason());
		claimDetails.setStatus("Pending");
		if (service.addClaim(claimDto.getPolicyId(), claimDetails) != 0) {
			Status1 status = new Status1();
			status.setStatus(StatusType1.Success);
			status.setMessage("OK");
			UserDetails user = service.findUserById(claimDto.getUserId());
			EmailUtility util = new EmailUtility();
			System.out.println(user.getUserId());
			System.out.println(user.getUserEmail());
			System.out.println(claimDetails.getClaimId());

			util.sendClaimRegisterEmail(mailSender, user.getUserEmail(), claimDetails.getClaimId());
			status.setClaimId(claimDetails.getClaimId());
			System.out.println(claimDetails.getClaimId());
			return status;

		} else {
			Status1 status = new Status1();
			status.setStatus(StatusType1.Failure);
			status.setMessage("Fail");
			return status;

		}

	}

	@PostMapping(path = "/listAllClaimDetailsForUser")
	public List<ClaimDetails> listAllClaimDetailsForUser(@RequestBody ShowDto showdto) {
		System.out.println(showdto.getUserId());
		System.out.println(service.getAllClaim(showdto.getUserId()));
		return service.getAllClaim(showdto.getUserId());
	}

	@PostMapping(path = "/findUserByPolicyId")
	public RenewObject findUserByPolicyId(@RequestBody PolicyIdObject pio) {
		RenewObject renew = new RenewObject();
		renew.setUserName(service.findUserByPolicyId(pio.getInsurancePolicyId()));
		renew.setPolicyType(service.findInsuranceByPolicyId(pio.getInsurancePolicyId()));
		return renew;

	}

	@PostMapping(path = "/renewInsurance")
	public LocalDate renewInsurance(@RequestBody PolicyIdObject pio) {
		VehicleInsuranceDetails vehicle = service.renewInsurance(pio.getInsurancePolicyId(),
				pio.getInsuranceDuration());
		UserDetails user = service.findUserByVehiclePolicyId(vehicle.getInsurancePolicyId());

		EmailUtility email = new EmailUtility();
		email.sendRenewEmail(mailSender, vehicle, user);

		return vehicle.getInsuranceDuration();
	}

	@GetMapping(path = "/getAllVehiclePolicies")
	public List<Object> getAllVehiclePolicies() {
		return service.getAllVehiclePolicies();
	}

	@GetMapping(path = "/getAllTravelPolicies")
	public List<Object> getAllTravelPolicies() {
		return service.getAllTravelPolicies();
	}

	
	@PostMapping(path = "/checkVehiclePolicyId")
	public boolean checkVehiclePolicyId(@RequestBody PolicyIdObject pio) {
		return service.checkVehiclePolicyId(pio.getInsurancePolicyId());
	}

	
//	public Status checkPolicyIdFromuser(@RequestBody PolicyIdObject pio) {
//
//		System.out.println(pio.getUserId());
//		System.out.println(pio.getInsurancePolicyId());
//		List<VehicleInsuranceDetails> vid = service.showUserVehicleInsurance(pio.getUserId());
//
//		for (VehicleInsuranceDetails details : vid) {
//
//			if (details.getInsurancePolicyId() == pio.getInsurancePolicyId()) {
//
//				Status status = new Status();
//
//				status.setStatus(StatusType.SUCCESS);
//
//				status.setMessage("OK");
//
//				return status;
//
//			}
//
//		}
//
//		Status status = new Status();
//
//		status.setStatus(StatusType.FAILURE);
//
//		status.setMessage("Fail");
//
//		return status;
//
//	}

	@PostMapping(path = "/getAllInsurance")
	public List<Object> getAllinsurance(@RequestBody DashDto dashDto) {
		List<Object> list = new ArrayList<>();
		List<VehicleInsuranceDetails> vl = service.showUserVehicleInsurance(dashDto.getUserId());
		List<TravelInsuranceDetails> tl = service.showUserTravelInsurance(dashDto.getUserId());
		list.addAll(tl);
		list.addAll(vl);
		return list;
	}

	@PostMapping(path = "/verifyEmail")
	public EmailStatus verifyEmail(@RequestBody ForgotPasswordDto forgotPasswordDto) {
		// System.out.println(forgotPasswordDto.getUserEmail());
		int otp = 0;
		try {
			otp = service.findByEmailforOTP(forgotPasswordDto.getUserEmail());
			EmailStatus emailStatus = new EmailStatus();
			EmailUtility emailUtility = new EmailUtility();
			emailUtility.sendOtpEmail(mailSender, otp, forgotPasswordDto.getUserEmail());
			emailStatus.setUserOTP(otp);
			emailStatus.setStatus(StatusType.SUCCESS);
			return emailStatus;

		} catch (ServiceException e) {

			EmailStatus status = new EmailStatus();
			status.setStatus(StatusType.FAILURE);
			// status.setOtp(otp);
			status.setMessage(e.getMessage());
			return status;
		}

	}

	@PostMapping(path = "/forgotPassword")
	public Status forgotPassword(@RequestBody ForgotPasswordDto forgotPassworddto) {

		try {
			service.forgotPassword(forgotPassworddto.getUserEmail(), forgotPassworddto.getnewUserPassword());
			EmailStatus emailStatus = new EmailStatus();
			EmailUtility emailUtility = new EmailUtility();
			emailUtility.resetPasswordEmail(mailSender, forgotPassworddto.getUserEmail());

			emailStatus.setStatus(StatusType.SUCCESS);
			return emailStatus;

		} catch (ServiceException e) {

			EmailStatus status = new EmailStatus();
			status.setStatus(StatusType.FAILURE);

			status.setMessage(e.getMessage());
			return status;
		}

	}

	public static class EmailStatus extends Status {
		private int userOTP;

		public int getUserOTP() {
			return userOTP;
		}

		public void setUserOTP(int userOTP) {
			this.userOTP = userOTP;
		}
	}
	

	@PostMapping(path = "/getAllVehiclepoliciesByUserId")
	public List<Object> getAllVehiclePoliciesByUserId(@RequestBody DashDto dashDto) {
		return service.getAllVehiclePoliciesByUserId(dashDto.getUserId());
	}

	@PostMapping(path = "/getTravelpoliciesByUserId")
	public List<Object> getAllTravelPoliciesByUserId(@RequestBody DashDto dashDto) {
		return service.getAllTravelPoliciesByUserId(dashDto.getUserId());
	}

	@PostMapping(path="/findPolicyIdByUserId")
	public Status findPolicyIdByUserId(@RequestBody PolicyIdObject pio) {
//		System.out.println(pio.getUserId());
//		System.out.println(pio.getInsurancePolicyId());
		Status st = new Status();
		boolean result = service.findPolicyIdByUserId(pio.getInsurancePolicyId(), pio.getUserId());
		if (result == true) {
			st.setStatus(StatusType.SUCCESS);
		} else {
			st.setStatus(StatusType.FAILURE);
		}
		
		return st;
	}

}
